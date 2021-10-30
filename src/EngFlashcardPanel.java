import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EngFlashcardPanel extends JPanel{
	
	
	private Word word;
	private JPanel panel;
	private JPanel CHANGEpanel;
	private JPanel NEXTpanel;
	private JPanel TEXTpanel;
	private JButton CHbutton;
	private JButton NEXTbutton;
	private JLabel TEXTlabel;
	
	
	public EngFlashcardPanel(Word word) throws SQLException {
		this.word=word;
		setpanel();
	}
	public void createCHANGEpanel() {
		CHANGEpanel=new JPanel();
		CHANGEpanel.setBackground(Color.white);
		CHbutton=new JButton("Chinese");
		CHANGEpanel.add(CHbutton);
		
	}
	
	public void createTEXTpanel() throws SQLException {
		TEXTpanel=new JPanel();
		TEXTpanel.setBackground(Color.white);
		
		
		
		TEXTlabel=new JLabel(word.getEnglish());
		Font font = new Font("Helvetica",Font.BOLD,35);
		TEXTlabel.setFont(font);
		TEXTpanel.add(TEXTlabel);
		
	}
	public void createNEXTpanel() {
		NEXTpanel=new JPanel();
		NEXTpanel.setBackground(Color.white);
		NEXTbutton=new JButton("Next");
		NEXTpanel.add(NEXTbutton);
		
	}
	public void setpanel() throws SQLException {
		createCHANGEpanel();
		createTEXTpanel();
		createNEXTpanel();
		panel=new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(3,1));
		panel.add(CHANGEpanel);
		panel.add(TEXTpanel);
		panel.add(NEXTpanel);
	}
	public JPanel getPanel() {
		return panel;
	}
	public void addActionListener(ActionListener ln) {
		this.CHbutton.addActionListener(ln);
	}
	public void addActionListenerSQL(ActionListener ln) {
		this.NEXTbutton.addActionListener(ln);
	}
	public void setTEXTlabel(Word word) {
		this.word=word;
		TEXTlabel.setText(word.getEnglish());
	}
	

}
