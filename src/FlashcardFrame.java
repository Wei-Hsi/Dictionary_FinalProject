import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlashcardFrame {
	private static final int  FRAME_WIDTH=300;
	private static final int  FRAME_HEIGHT=400;
	private SQL sql;
	private ArrayList<QuizWord> words;
	private QuizWord Cword;
	private String wordBank;
	public FlashcardFrame(String wordBank) {
		this.wordBank=wordBank;
	}
	public void start() throws SQLException {
		
		sql=new SQL();
		Word word=sql.randomWord(wordBank);
	
		
		
		JFrame mainFrame = new JFrame("Flashcard");
		CardLayout myCard=new CardLayout();
		JPanel panelCont=new JPanel();
		panelCont.setLayout(myCard);
		
		EngFlashcardPanel EN=new EngFlashcardPanel(word);
		ChiFlashcardPanel CH=new ChiFlashcardPanel(word);
		
		panelCont.add(EN.getPanel(),"1");
		panelCont.add(CH.getPanel(),"2");
		myCard.show(panelCont,"1");
		
		
		
		 class ButtonListener1 implements ActionListener{
		    	public void actionPerformed(ActionEvent event) {
		    		
		    		myCard.next(panelCont);
		    	}
		    }
		ActionListener ln1 = new ButtonListener1();
		
		 class ButtonListener2 implements ActionListener{
		    	public void actionPerformed(ActionEvent event) {
		    		myCard.previous(panelCont);
		    	}
		 }
		 
		 
		 class ButtonListener3 implements ActionListener{
			 public void actionPerformed(ActionEvent event) {
	    		Word word;
				try {
					word = sql.randomWord(wordBank);
					EN.setTEXTlabel(word);
		    		CH.setTEXTlabel(word);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
		ActionListener ln2= new ButtonListener2();
		
		ActionListener ln3=new ButtonListener3();
		
		EN.addActionListener(ln1);
		CH.addActionListener(ln2);
		EN.addActionListenerSQL(ln3);
		mainFrame.add(panelCont);
		
		mainFrame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
	    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    mainFrame.setVisible(true);
		    	
	}
	
}
