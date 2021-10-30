import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EnglishTestFrame {
	
	private static final int  FRAME_WIDTH=500;
	private static final int  FRAME_HEIGHT=350;
	private SQL sql;
	private String wordBank;
	OpeningPanel op;
	EnglishTestPanel En;
	EndingPanel ending;
	
	public  EnglishTestFrame(String wordBank) {
		this.wordBank=wordBank;
	}
	
	public void start() throws SQLException {
		
		JFrame mainFrame = new JFrame("English Test");
		CardLayout myCard=new CardLayout();
		JPanel panelCont=new JPanel();
		panelCont.setLayout(myCard);
		
		op=new OpeningPanel();
		En=new EnglishTestPanel(wordBank);
		ending=new EndingPanel(5);
		
		panelCont.add(op.getPanel(),"1");
		panelCont.add(En.getPanel(),"2");
		panelCont.add(ending.getPanel(),"3");
		myCard.show(panelCont,"1"); 
		
	
		class ButtonListener2 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		myCard.show(panelCont,"2");
	    	}
	    }
		
		class ButtonListener3 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		
	    		ending.setErrorList(En.getErrorList());
	    		ending.setReadyTest(En.getReadyTest());
	    		ending.setAddToList(En.getAddToList());
	    		ending.setCTest(En.getCTest());
	    		ending.end();
	    		
	    		myCard.show(panelCont,"3");
	    	}
	    }
		
		class ButtonListener1 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		try {
	    			En=new EnglishTestPanel(wordBank);
	    			panelCont.add(En.getPanel(),"2");
	    			ActionListener ln3 = new ButtonListener3();
	    			En.addActionListener(ln3);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    		myCard.show(panelCont,"1");
	    	}
	    }
	
		ActionListener ln1 = new ButtonListener1();
		ActionListener ln2 = new ButtonListener2();
		ActionListener ln3 = new ButtonListener3();
		 
		op.addActionListener(ln2);
		En.addActionListener(ln3);
		ending.addActionListener(ln1);

		mainFrame.add(panelCont);
		
		mainFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
	    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    mainFrame.setVisible(true);
	}
}
