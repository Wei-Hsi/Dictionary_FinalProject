import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class EndingPanel extends JPanel{
	private JPanel panel0;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel;
	private JLabel QLabel1;
	private JLabel QLabel2;
	private JLabel QLabel3;
	private JLabel spaceLabel;
	private JTextArea TextArea0;
	private JButton reButton;
	private JPanel repanel;
	private int ReadyTest=0;
	private int CTest=0;
	int wrong;
	double ratio;
	String Sratio;
	private ArrayList<String>errorList;
	private ArrayList<Word>addToList;
	private JButton addButton;
	private SQL sql;
	
	private JLabel resultLabel;
	private JButton retakeBtn;
	
	public EndingPanel(int Cnumber) throws SQLException {
		sql=new SQL();
		addToList=new ArrayList<Word>();
		errorList=new ArrayList<String>();
		setEnding();
		
	}
	public void createPanel0() {
		panel0=new JPanel();
		panel0.setLayout(new GridLayout());
		TextArea0=new JTextArea(500,50);
		TextArea0.setLineWrap(true);
		
		JScrollPane scrollpane = new JScrollPane(TextArea0);

		panel0.add(scrollpane);
		panel0.setBackground(new Color(22,74,144));
	}
	
	public void createPanel1() {
		
		panel1=new JPanel();
		panel1.setBackground(new Color(22,74,144));
		
		resultLabel = new JLabel("Test Result");
		Font resultFont = new Font("Helvetica",Font.BOLD,30);
		resultLabel.setFont(resultFont);
		resultLabel.setForeground(new Color(254,196,0));
		
		panel1.add(resultLabel);					
	}
	public void createPanel2() {
		panel2=new JPanel();
		panel2.setBackground(new Color(22,74,144));
		panel3 = new JPanel();
		panel3.setBackground(new Color(22,74,144));
		//panel4 = new JPanel();
		//panel4.setBackground(new Color(22,74,144));
		
		QLabel1=new JLabel();	
		Font font1 = new Font("Helvetica",Font.BOLD,20);
		QLabel1.setFont(font1);
		QLabel1.setForeground(Color.white);
		
		spaceLabel = new JLabel("  /  ");
		Font font = new Font("Helvetica",Font.BOLD,20);
		spaceLabel.setFont(font);
		spaceLabel.setForeground(Color.white);
		
		QLabel2=new JLabel();
		Font font2 = new Font("Helvetica",Font.BOLD,20);
		QLabel2.setFont(font2);
		QLabel2.setForeground(Color.white);
		//panel2.setBackground(new Color(22,74,144));
		
		QLabel3=new JLabel();
		Font font3 = new Font("Helvetica",Font.BOLD,20);
		QLabel3.setFont(font3);
		QLabel3.setForeground(Color.white);
		//panel2.setBackground(new Color(22,74,144));
		
		panel2.add(QLabel1);
		panel2.add(spaceLabel);
		panel2.add(QLabel2);
		panel3.add(QLabel3);
	}
	
	public void createRePanel() {
		repanel=new JPanel();
		reButton=new JButton("Retake");
		addButton=new JButton("Add wrong words to the folder");
		
		class ButtonListener implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		try { 
	    			for(Word word:addToList){
						sql.addToIncorrect(word);
	    			}
	    			JOptionPane.showMessageDialog(panel0.getParent(),"Wrong words have been added to the 'Incorrect' folder.");
			  } catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();				
	    		}
	    	}
	    }
		ActionListener ln = new ButtonListener();
		 
		addButton.addActionListener(ln);
		
		/*
		retakeBtn = new JButton("retake");
		
		class ButtonListener2 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		
	    	}
	    }
		ActionListener retake = new ButtonListener();
		retakeBtn.addActionListener(retake);
		*/
		
		repanel.add(addButton);
		repanel.add(reButton);
		repanel.setBackground(new Color(22,74,144));
		
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void setEnding() {
		panel=new JPanel();
		
		createPanel0();
		createPanel1();
		createPanel2();
		createRePanel();
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		//panel.add(panel4);
		
		panel.add(repanel);
		panel.add(panel0);
		
		GridLayout GLayout=new GridLayout(5,1);
		panel.setLayout(GLayout);
		panel.setBackground(new Color(22,74,144));
	}
	public void addActionListener(ActionListener ln) {
		this.reButton.addActionListener(ln);
	}
	
	public void setCTest(int CTest) {
		this.CTest=CTest;
	}
	public void setReadyTest(int ReadyTest) {
		this.ReadyTest=ReadyTest;
	}
	public void end() {
		String errorResult=String.format("%-10s%-30s%-40s%-10s\n","Number","Question","Answer","Your choice");
		for(int i=0;i<errorList.size();i+=4){
			errorResult+=String.format("%-15s%-25s%-40s%-10s\n",
					errorList.get(i),errorList.get(i+1),errorList.get(i+2),errorList.get(i+3));
			}	
		TextArea0.setText(errorResult);
		QLabel1.setText("Correct:   "+CTest);
		wrong=ReadyTest-CTest;
		QLabel2.setText("Wrong:  "+" "+wrong);
		if(ReadyTest>0) {
			double DReadyTest=ReadyTest;
			double DCTest=CTest;	
			ratio=DCTest/DReadyTest*100;
			int iratio=(int) ratio;
			QLabel3.setText("Accuracy:  "+iratio+"%");
		}else {
			QLabel3.setText("You did not answer the test.");
		}
		
	}
	public void setErrorList(ArrayList<String> errorList) {
		this.errorList=errorList;
	}
	public void clearErrorList() {
		errorList.removeAll(errorList);
	}
	public void setAddToList(ArrayList<Word>addToList){
		this.addToList=addToList;
	}
	
}
