import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchResultPanel extends JPanel {
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 450;
	
	private static final int FIELD_WIDTH = 16;
	
	private String Eword;
	private VocabularyMgr vocabularies;
	
	private String inputEword;
	private String inputPOS;
	private String inputCword;
	
	private final ExecSQL exec;
	private JPanel searchBarPanel2; 
	private JButton searchButton2;
	private JTextField searchField2; 
	
	private JPanel panel;
	private JPanel wordPanel; 
	private JPanel meaningPanel;
	private JPanel addFolderPanel; 
	private JLabel wordLabel;
	private JLabel folderLabel;
	private JLabel feedbackLabel;
	//private JButton backButton;
	private JButton addButton;
	
	public SearchResultPanel(String Eword, VocabularyMgr vocabularies) throws SQLException {
		this.exec = new ExecSQL();
		setLayout(new CardLayout());
		//panel = new JPanel();
		//panel.setLayout(new CardLayout());
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		showResult(Eword, vocabularies);
		createaddFolderComp();
		createwordComp();
		createPanel();
	}

	private void createaddFolderComp() {
		// TODO Auto-generated method stub
		addFolderPanel = new JPanel();
		folderLabel = new JLabel("Add to folder");
		addButton = new JButton("add");
		feedbackLabel = new JLabel("");
		
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//folderBtn.setBackground(Color.BLACK);
				for (Vocabulary vocabulary : vocabularies.getVocabularies()) {
					try {
						if(exec.addNewWord(Eword, vocabulary.getPOS(), vocabulary.getCword())) {
							feedbackLabel.setText("The word has added to the NewWord folder!");
						} else {
							feedbackLabel.setText("Operation failure");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		ActionListener listener = new ClickListener();
		addButton.addActionListener(listener);
		
		addFolderPanel.add(folderLabel);
		addFolderPanel.add(addButton);
		addFolderPanel.add(feedbackLabel);
	}
	private void createwordComp() {
		// TODO Auto-generated method stub
		wordPanel = new JPanel();
		meaningPanel = new JPanel();
		wordPanel.setLayout(new BorderLayout());
		meaningPanel.setLayout(new GridLayout(10, 1));
		//wordPanel.setBackground(new Color(221,219,220));
		
		wordLabel = new JLabel(Eword);
		Font wordFont = new Font("DIALOG",Font.BOLD,35);
		wordLabel.setFont(wordFont);
		
		//backButton = new JButton("back");
		
		//wordPanel.add(backButton);
		wordPanel.add(wordLabel, BorderLayout.NORTH);
		
		for (Vocabulary vocabulary : vocabularies.getVocabularies()) {
			JLabel posLabel = new JLabel(vocabulary.getPOS());
			Font posFont = new Font("SERIF",Font.ITALIC,16);
			posLabel.setFont(posFont);
			posLabel.setForeground(Color.blue);
			meaningPanel.add(posLabel);
			
			JLabel meaningLabel = new JLabel(vocabulary.getCword());
			Font meaningFont = new Font("MONOSPACED",Font.PLAIN,16);
			meaningLabel.setFont(meaningFont);
			meaningPanel.add(meaningLabel);
		}
		wordPanel.add(meaningPanel, BorderLayout.CENTER);
	}
	
	public void showResult(String Eword, VocabularyMgr vocabularies) {
		System.out.println(Eword);
		this.Eword = Eword;
		this.vocabularies = vocabularies;
	}

	private void createPanel() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		createwordComp();
		createaddFolderComp();
		createsearchBarComp();
		
		panel.add(searchBarPanel2,BorderLayout.NORTH);
		panel.add(wordPanel,BorderLayout.CENTER);
		panel.add(addFolderPanel,BorderLayout.SOUTH);
		add(panel,BorderLayout.CENTER);
	}
	private void createsearchBarComp() {
		// TODO Auto-generated method stub
		searchBarPanel2 = new JPanel();
		searchBarPanel2.setBackground(new Color(22,74,144));
		//searchButton2 = new JButton("search");
		
		URL url = SearchResultPanel.class.getResource("/img/search.png");
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH));
		searchButton2 = new JButton(icon);
		searchButton2.setBackground(new Color(238,185,5)); //���s�L�k�ܦ�
		
		searchField2 = new JTextField(FIELD_WIDTH);
		searchBarPanel2.add(searchField2);
		//searchBarPanel2.add(searchButton2);
		searchBarPanel2.add(searchButton2);
	}
	
	public void addButtonListener(JPanel panel) {
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String word = searchField2.getText();
					if(exec.findWord(word)) {
						exec.addSearchingWord(searchField2.getText());
				
						SearchResultPanel searchResultPage = new SearchResultPanel(word, exec.getVocabularyMgr());
						searchResultPage.addButtonListener(panel);						
						panel.add(searchResultPage, "2");
						CardLayout cardLayout = (CardLayout) panel.getLayout();
						cardLayout.show(panel, "2");	
					} else {
						int selection = JOptionPane.showConfirmDialog(panel.getParent(), "The word is not in 7000_word folder!\nDo you want to add the new word?","Yes",JOptionPane.OK_CANCEL_OPTION);
						if (selection == JOptionPane.OK_OPTION) {
							inputEword = JOptionPane.showInputDialog(panel.getParent(), "Please input the word:");
							inputPOS = JOptionPane.showInputDialog(panel.getParent(), "Please input the part of the speech of the word:");
							inputCword = JOptionPane.showInputDialog(panel.getParent(), "Please input the Chinese meaning of the word:");
							exec.createNewWord(inputEword, inputPOS, inputCword);
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							JOptionPane.showMessageDialog(panel.getParent(),"The word will not be added into the 'NewWord' folder.");
						}
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
					e2.getMessage();
				}
			}
		}
		ActionListener listener = new ClickListener();
		searchButton2.addActionListener(listener);
	}
}

