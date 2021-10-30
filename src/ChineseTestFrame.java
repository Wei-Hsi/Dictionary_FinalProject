import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChineseTestFrame {
	private static final int  FRAME_WIDTH=500;
	private static final int  FRAME_HEIGHT=350;
	private SQL sql;
	private String wordBank;
	OpeningPanel op;
	ChineseTestPanel ch;
	EndingPanel ending;
	public  ChineseTestFrame(String wordBank) {
		this.wordBank=wordBank;
	}
	
	public void start() throws SQLException {
		JFrame mainFrame = new JFrame("Chinese Test");
		CardLayout myCard=new CardLayout();
		JPanel panelCont=new JPanel();
		panelCont.setLayout(myCard);
		
		op=new OpeningPanel();
		ch=new ChineseTestPanel(wordBank);
		ending=new EndingPanel(5);
		
		panelCont.add(op.getPanel(),"1");
		panelCont.add(ch.getPanel(),"2");
		panelCont.add(ending.getPanel(),"3");
		myCard.show(panelCont,"1");
	
		
		class ButtonListener2 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		myCard.show(panelCont,"2");
	    	}
	    }
		
		class ButtonListener3 implements ActionListener{
	    	public void actionPerformed(ActionEvent event) {
	    		
	    		ending.setErrorList(ch.getErrorList());
	    		ending.setReadyTest(ch.getReadyTest());
	    		ending.setAddToList(ch.getAddToList());
	    		ending.setCTest(ch.getCTest());
	    		ending.end();
	    		
	    		myCard.show(panelCont,"3");
	    	}
	    }
		
		class ButtonListener1 implements ActionListener{
		    public void actionPerformed(ActionEvent event) {
	    		try {
	    			ch=new ChineseTestPanel(wordBank);
	    			panelCont.add(ch.getPanel(),"2");
	    			ActionListener ln3 = new ButtonListener3();
	    			ch.addActionListener(ln3);
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
		ch.addActionListener(ln3);
		ending.addActionListener(ln1);
		

	
		mainFrame.add(panelCont);
		
		mainFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
	    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    mainFrame.setVisible(true);
	}
}
