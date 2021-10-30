import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ChiFlashcardPanel extends JPanel{
	
	private Word word;
	private JPanel panel;
	private JPanel CHANGEpanel;
	private JPanel NEXTpanel;
	private JPanel TEXTpanel;
	private JPanel wordPanel;
	private JButton ENbutton;
	private JTextArea TEXTlabel;
	
	private JLabel textLabel;
	
	
	public ChiFlashcardPanel(Word word) {
		this.word=word;
		setpanel();
	}
	public void createCHANGEpanel() {
		CHANGEpanel=new JPanel();
		ENbutton=new JButton("English");
		CHANGEpanel.setBackground(Color.white);
		CHANGEpanel.add(ENbutton);
		
	}
	
	public void createTEXTpanel() {
		wordPanel = new JPanel();
		wordPanel.setBackground(Color.white);
		wordPanel.setLayout(new GridLayout(5, 1));
		TEXTpanel=new JPanel();
		TEXTpanel.setBackground(Color.white);
		
		for(String ch:word.getChinese()) {
			JLabel tmp = new JLabel(ch, JLabel.CENTER);
			Font textFont = new Font("SERIF",Font.PLAIN,25);
			tmp.setFont(textFont);
			wordPanel.add(tmp);
		}
		
		TEXTpanel.add(wordPanel, BorderLayout.CENTER);
	}

	
	public void setpanel() {
		createCHANGEpanel();
		
		createTEXTpanel();
		panel=new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new GridLayout(0 ,1));
		panel.add(CHANGEpanel,BorderLayout.NORTH);
		panel.add(TEXTpanel,BorderLayout.CENTER);

	}
	public JPanel getPanel() {
		return panel;
	}
	public void addActionListener(ActionListener ln) {
		this.ENbutton.addActionListener(ln);
	}
	public void setTEXTlabel(Word word) {
		this.word=word;
		wordPanel.removeAll();
		
		for(String ch:word.getChinese()) {
			JLabel tmp = new JLabel(ch, JLabel.CENTER);
			Font textFont = new Font("SERIF",Font.PLAIN,25);
			tmp.setFont(textFont);
			wordPanel.add(tmp);
		}
	}

}
