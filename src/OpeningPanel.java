import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OpeningPanel extends JPanel{
	private JPanel wpanel1;
	private JPanel wpanel2;
	private JPanel panel;
	private JPanel STARTpanel;
	private JButton STARTButton;
	private JLabel welcomeLabel;
	
	
	public OpeningPanel(){
		setPanel();
	}
	
	public void createSTARTpanel() {
		STARTpanel=new JPanel();
		STARTButton=new JButton("Start");
		
		STARTpanel.add(STARTButton);
		STARTpanel.setBackground(new Color(22,74,144));
	}
	
	public void setPanel() {
		createSTARTpanel();
		wpanel1=new JPanel();
		wpanel2=new JPanel();
		
		wpanel1.setBackground(new Color(22,74,144));
		wpanel2.setBackground(new Color(22,74,144));
		
		welcomeLabel = new JLabel("* Welcome to the test *");
		Font wordFont = new Font("DIALOG",Font.BOLD,35);
		welcomeLabel.setFont(wordFont);
		welcomeLabel.setForeground(Color.white);
		
		wpanel2.add(welcomeLabel);
		
		panel=new JPanel();
		
		panel.add(wpanel1);
		panel.add(wpanel2);
		panel.add(STARTpanel);
		
		panel.setBackground(new Color(22,74,144));
		panel.setLayout(new GridLayout(3,1));
	}
	public JPanel getPanel() {
		return panel;
	}
	public void addActionListener(ActionListener ln) {
		this.STARTButton.addActionListener(ln);
	    
	}
}
