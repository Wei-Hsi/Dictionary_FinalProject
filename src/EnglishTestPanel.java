import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EnglishTestPanel extends JPanel{
	private static final int  Panel_WIDTH=500;
	private static final int  Panel_HEIGHT=350;

	
	private String number;
	private JPanel panel;
	
	private JPanel Hpanel;
	private JPanel Qpanel;
	private JPanel Apanel1;
	private JPanel Apanel2;
	private JLabel HLabelP;
	private JLabel HLabelN;
	private JLabel QLabel;
	private JLabel A1Label;
	private JLabel A2QLabel;
	private JLabel A3QLabel;
	private JLabel A4QLabel;
	private JButton A1Button;
	private JButton A2Button;
	private JButton A3Button;
	private JButton A4Button;
	private CardLayout myCard;
	private JPanel panelCont;
	private SQL sql;
	private int ReadyTest=0;
	private int CTest=0;
	private ArrayList<QuizWord> words;
	private String answer;
	private JPanel endPanel;
	private JButton endButton;
	private int ReadiedTest=1;
	private ArrayList<String>errorList;
	private Word answerWord;
	private ArrayList<Word>addToList;
	private String wordBank;
	
	
	public EnglishTestPanel(String wordBank) throws SQLException{
		this.wordBank=wordBank;
		addToList=new ArrayList<Word>();
		errorList=new ArrayList<String>();
		sql=new SQL();
		words=sql.createQuiz(wordBank);
		setpanel();


	}
	
	public void setHpanel() {
		Hpanel=new JPanel();
		
		//HLabelP=new JLabel();
		
		HLabelN=new JLabel("Question "+ReadiedTest);

		
		Hpanel.setPreferredSize(new Dimension(500,30));
		Color color=new Color(0,100,255,100);
		Hpanel.setBackground(new Color(5,128,232));
		Font font = new Font("Helvetica",Font.BOLD,15);
		HLabelN.setFont(font);

		HLabelN.setForeground(new Color(254,196,0));
		
		//Hpanel.add(HLabelP);
		Hpanel.add(HLabelN);

	}
	

	public void setQpanel() throws SQLException{
		Qpanel=new JPanel();
		
		
		for(QuizWord word:words) {
			if(word.getAnswer()==true) {
				QLabel=new JLabel(word.getChinese(0));
				answer=word.getChinese(0);
				answerWord=word;
			}
		}
						
		Color color=new Color(0,150,255,100);
		
		Font font = new Font("Helvetica",Font.BOLD,30);
		QLabel.setFont(font);
		QLabel.setForeground(Color.white);
		Qpanel.setBackground(new Color(22,74,144));
		Qpanel.setPreferredSize(new Dimension(500,80));
		Qpanel.add(QLabel);
		//Border etchedBorder = BorderFactory.createEtchedBorder();
		//Border titleBorder = BorderFactory.createTitledBorder(etchedBorder,"�D��");
		//Qpanel.setBorder(titleBorder);
	}
	
	public void setApanel1(){
		 
		
		Apanel1=new JPanel();
		A1Label=new JLabel();
		A2QLabel=new JLabel();
		A1Button=new JButton(words.get(0).getEnglish());
		A2Button=new JButton(words.get(1).getEnglish());
		
		//A1Button.setBackground(Color.gray); 
		//A1Button.setForeground(Color.white);
		//A2Button.setBackground(Color.gray); 
		//A2Button.setForeground(Color.white);
		A1Button.setPreferredSize(new Dimension(150,40));
		A2Button.setPreferredSize(new Dimension(150,40));
		
		Apanel1.add(A1Label);
		Apanel1.add(A1Button);
		Apanel1.add(A2QLabel);
		Apanel1.add(A2Button);
		Apanel1.setBackground(new Color(22,74,144));
		 class ButtonListener1 implements ActionListener{
		    	public void actionPerformed(ActionEvent event) {
		    		try {
		    			if(A1Button.getText().equals(sql.findEnglish(answer,wordBank))) {
							CTest+=1;
						}else {
							errorList.add(ReadiedTest+"");
							errorList.add(answerWord.getChinese(0));
							errorList.add(answerWord.getEnglish());
							errorList.add(A1Button.getText());
							addToList.add(answerWord);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		ReadyTest+=1;
		    		ReadiedTest=ReadiedTest+1;
		    		
		    		try {
						words=sql.createQuiz(wordBank);
						for(QuizWord word:words) {
							if(word.getAnswer()==true) {
								QLabel.setText(word.getChinese(0));
								answer=word.getChinese(0);
								answerWord=word;
							}
							A1Button.setText(words.get(0).getEnglish());
							A2Button.setText(words.get(1).getEnglish());
							A3Button.setText(words.get(2).getEnglish());
							A4Button.setText(words.get(3).getEnglish());
							HLabelN.setText("Question "+ReadiedTest);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		 ActionListener ln1 = new ButtonListener1();
		 A1Button.addActionListener(ln1);
		 class ButtonListener2 implements ActionListener{
		 public void actionPerformed(ActionEvent event) {
	    		try {
	    			if(A2Button.getText().equals(sql.findEnglish(answer,wordBank))) {
						CTest+=1;
					}else {
						errorList.add(ReadiedTest+"");
						errorList.add(answerWord.getChinese(0));
						errorList.add(answerWord.getEnglish());
						errorList.add(A2Button.getText());
						addToList.add(answerWord);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		ReadyTest+=1;
	    		ReadiedTest=ReadiedTest+1;
	    		try {
					words=sql.createQuiz(wordBank);
					for(QuizWord word:words) {
						if(word.getAnswer()==true) {
							QLabel.setText(word.getChinese(0));
							answer=word.getChinese(0);
							answerWord=word;
						}
						A1Button.setText(words.get(0).getEnglish());
						A2Button.setText(words.get(1).getEnglish());
						A3Button.setText(words.get(2).getEnglish());
						A4Button.setText(words.get(3).getEnglish());
						HLabelN.setText("Question "+ReadiedTest);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }
		 ActionListener ln2 = new ButtonListener2();
		 A2Button.addActionListener(ln2);
	}
		
		
		

	
	public void setApanel2(){
		Apanel2=new JPanel();
		Apanel2.setOpaque(false);
		A3QLabel=new JLabel();
		A4QLabel=new JLabel();
		
		A3Button=new JButton(words.get(2).getEnglish());
		A4Button=new JButton(words.get(3).getEnglish());
		A3Button.setPreferredSize(new Dimension(150,40));
		A4Button.setPreferredSize(new Dimension(150,40));
		//A3Button.setBackground(Color.gray); 
		//A3Button.setForeground(Color.white);
		//A4Button.setBackground(Color.gray); 
		//A4Button.setForeground(Color.white);
		Apanel2.add(A3QLabel);
		Apanel2.add(A3Button);
		Apanel2.add(A4QLabel);
		Apanel2.add(A4Button);	
		Apanel2.setBackground(new Color(22,74,144));
		
		 class ButtonListener3 implements ActionListener{
		    	public void actionPerformed(ActionEvent event) {
		    		try {
		    			if(A3Button.getText().equals(sql.findEnglish(answer,wordBank))) {
							CTest+=1;
						}else {
							errorList.add(ReadiedTest+"");
							errorList.add(answerWord.getChinese(0));
							errorList.add(answerWord.getEnglish());
							errorList.add(A3Button.getText());
							addToList.add(answerWord);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		ReadyTest+=1;
		    		ReadiedTest=ReadiedTest+1;
		    		try {
						words=sql.createQuiz(wordBank);
						for(QuizWord word:words) {
							if(word.getAnswer()==true) {
								QLabel.setText(word.getChinese(0));
								answer=word.getChinese(0);
								answerWord=word;
							}
							A1Button.setText(words.get(0).getEnglish());
							A2Button.setText(words.get(1).getEnglish());
							A3Button.setText(words.get(2).getEnglish());
							A4Button.setText(words.get(3).getEnglish());
							HLabelN.setText("Question "+ReadiedTest);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		 ActionListener ln3 = new ButtonListener3();
		 A3Button.addActionListener(ln3);
		 
		 class ButtonListener4 implements ActionListener{
		    	public void actionPerformed(ActionEvent event) {
		    		try {
		    			if(A4Button.getText().equals(sql.findEnglish(answer,wordBank))) {
							CTest+=1;
						}else {
							errorList.add(ReadiedTest+"");
							errorList.add(answerWord.getChinese(0));
							errorList.add(answerWord.getEnglish());
							errorList.add(A4Button.getText());
							addToList.add(answerWord);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		ReadyTest+=1;
		    		ReadiedTest=ReadiedTest+1;
		    		try {
						words=sql.createQuiz(wordBank);
						for(QuizWord word:words) {
							if(word.getAnswer()==true) {
								QLabel.setText(word.getChinese(0));
								answer=word.getChinese(0);
								answerWord=word;
								
							}
							A1Button.setText(words.get(0).getEnglish());
							A2Button.setText(words.get(1).getEnglish());
							A3Button.setText(words.get(2).getEnglish());
							A4Button.setText(words.get(3).getEnglish());
							HLabelN.setText("Question "+ReadiedTest);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		 ActionListener ln4 = new ButtonListener4();
		 A4Button.addActionListener(ln4);
		 
		 		
	}
	public void setendPanel() {
		endPanel=new JPanel(); 
		endButton=new JButton("end");
		endButton.setPreferredSize(new Dimension(180,30));
		
		endPanel.setBackground(new Color(22,74,144));
		endPanel.add(endButton);
		
	}
	
	
	

	
	public void setpanel() throws SQLException{
		setHpanel();
		setQpanel();
		setApanel1();
		setApanel2();
		setendPanel();
		panel=new JPanel();
		Color color=new Color(0,150,255,100);
		panel.add(Hpanel);
		panel.add(Qpanel);
		panel.add(Apanel1);
		panel.add(Apanel2);
		panel.add(endPanel);
		//panel.setLayout(new GridLayout(5,1));
		panel.setBackground(new Color(22,74,144));


	}


	public void addActionListener(ActionListener ln) {
		this.endButton.addActionListener(ln);
	   	
	}
	
  
	public JPanel getPanel() {
		return panel;
	}
	
	public JPanel getPanelCont() {
		return panelCont;
	}
	public int getReadyTest() {
		return ReadyTest;
	}
	public int getCTest() {
		return CTest;
	}
	public ArrayList<String> getErrorList(){
		return errorList;
	}
	public ArrayList<Word> getAddToList() {
		return addToList;
	}
}
