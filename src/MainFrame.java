import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 450;
	
	private SearchPanel searchPage;
	private FolderPanel folderPage;
	
	private JPanel panel; 
	private JPanel titlePanel;
	private JPanel optionPanel;
	private JLabel imgLabel;
	private JButton dictionaryBtn;
	private JButton folderBtn; 
	
	
	public MainFrame() throws SQLException {		
		
		setLayout(new BorderLayout());
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setTitle("Dictionary");
		
		searchPage = new SearchPanel();
		//searchResultPage = new SearchResultPanel();
		folderPage = new FolderPanel();
		
		panel = new JPanel();
		panel.setLayout(new CardLayout());
		
		panel.add(searchPage, "1");
		//panel.add(searchResultPage, "2");
		panel.add(folderPage,"3");
		
		searchPage.addButtonListener(panel);
		searchPage.addTableModelListener(panel);
		//searchResultPage.addButtonListener(panel);
		
		createTitleComp();
		
		add(titlePanel,BorderLayout.NORTH);
		add(panel,BorderLayout.CENTER);
	}
	private void createTitleComp() {
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(new Color(5,128,232));
		
		
		URL url = MainFrame.class.getResource("/img/read.png");
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH));
		imgLabel = new JLabel(icon, JLabel.CENTER);
		imgLabel.setSize(getPreferredSize());
		
		titlePanel.add(imgLabel, BorderLayout.WEST);
		
		JLabel OOP = new JLabel("OOP Dictionary");
		Font wordFont = new Font("DIALOG",Font.BOLD,18);
		OOP.setForeground(Color.WHITE);
		OOP.setFont(wordFont);
		titlePanel.add(OOP);
		
		optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout());
		optionPanel.setBackground(new Color(5,128,232));
		dictionaryBtn = new JButton("Dictionary");		
		dictionaryBtn.setForeground(new Color(254,196,0));
		dictionaryBtn.setBorder(null);
		optionPanel.add(dictionaryBtn, BorderLayout.CENTER);
		
		folderBtn = new JButton("Folder");
		folderBtn.setBorder(null);
		folderBtn.setForeground(new Color(254,196,0));
		
		//�s��folderPage����
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//folderBtn.setBackground(Color.BLACK);
				CardLayout cardLayout = (CardLayout) panel.getLayout();
				cardLayout.show(panel, "3");
			}
		}
		ActionListener listener = new ClickListener();
		folderBtn.addActionListener(listener);
		
		class ClickListener2 implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//folderBtn.setBackground(Color.BLACK);
				try {
					SearchPanel searchPage = new SearchPanel();
					panel.add(searchPage, "1");
					searchPage.addButtonListener(panel);
					searchPage.addTableModelListener(panel);
					CardLayout cardLayout = (CardLayout) panel.getLayout();
					cardLayout.show(panel, "1");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		ActionListener listener2 = new ClickListener2();
		dictionaryBtn.addActionListener(listener2);
		
		optionPanel.add(folderBtn, BorderLayout.EAST);
		titlePanel.add(optionPanel, BorderLayout.EAST);
	}

	
}