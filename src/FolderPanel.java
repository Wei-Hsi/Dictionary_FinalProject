import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FolderPanel extends JPanel{
	private JComboBox<String> wordBankBox;
	private JLabel wordBankLabel;
	private JButton flashcardB,cTestB,eTestB,matchGameB;
	private SQL sql;
	
	public FolderPanel() throws SQLException {
	//construct panel	
		setLayout(new GridBagLayout());
		sql=new SQL();
		wordBankBox = new JComboBox();
		wordBankLabel = new JLabel("Choose a folder:");
		wordBankBox.addItem("7000word_1");
		wordBankBox.addItem("7000word_2");
		wordBankBox.addItem("NewWord");
		wordBankBox.addItem("Incorrect");
		
		CreateFlashcardButton();
		CreateCTestButton();
		CreateETestButton();
		//CreateMatchGameButton();
		
		ConstructPanel();
	}
	
	public void CreateFlashcardButton() {
		//create button + inner class,show panel when clicked, show error when no WordBank selected
		flashcardB=new JButton("   Flashcard  ");
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String wordBank=(String) wordBankBox.getSelectedItem();
				
				FlashcardFrame ch = new FlashcardFrame(wordBank);
			
				if(wordBank!=null) {
					try {
						if(sql.returnWords(wordBank)>1) {
						try {
							ch.start();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}}
						else {
							JOptionPane.showMessageDialog(new JFrame(),"No words in this folder!", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(),"Please select a folder.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		ClickListener listener=new ClickListener();
		flashcardB.addActionListener(listener);
	}
	public void CreateCTestButton() {
		cTestB=new JButton("Chinese Test");
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String wordBank=(String) wordBankBox.getSelectedItem();
				
				if(wordBank!=null) {
					ChineseTestFrame ch=new ChineseTestFrame(wordBank);
					try {
						if(sql.returnWords(wordBank)>4) {
							try {
								ch.start();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}}
							else {
								JOptionPane.showMessageDialog(new JFrame(),"No enough words to start a test!(min:4)", "Error", JOptionPane.ERROR_MESSAGE);
							}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(),"Please select a folder.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		ClickListener listener=new ClickListener();
		cTestB.addActionListener(listener);
	}
	public void CreateETestButton() {
		eTestB=new JButton(" English Test");
		class ClickListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String wordBank=(String) wordBankBox.getSelectedItem();
				
				if(wordBank!=null) {
					EnglishTestFrame en=new EnglishTestFrame(wordBank);
					try {
						if(sql.returnWords(wordBank)>4) {
							try {
								en.start();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}}
							else {
								JOptionPane.showMessageDialog(new JFrame(),"No enough words to start a test!(min:4)", "Error", JOptionPane.ERROR_MESSAGE);
							}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(),"Please select a folder.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		ClickListener listener=new ClickListener();
		eTestB.addActionListener(listener);
	}
	
	public void ConstructPanel() {
		GridBagConstraints label = new GridBagConstraints();
		label.gridx = 1;
		label.gridy = 0;
		label.gridwidth = 1;
		label.gridheight = 1;
		add(wordBankLabel,label);
		
		GridBagConstraints combobox = new GridBagConstraints();
		combobox.gridx = 2;
		combobox.gridy = 0;
		combobox.gridwidth = 1;
		combobox.gridheight = 1;
		add(wordBankBox,combobox);
		
		GridBagConstraints flashcard = new GridBagConstraints();
		flashcard.ipady = 15;
		flashcard.gridx = 1;
		flashcard.gridy = 1;
		flashcard.gridwidth = 2;
		flashcard.gridheight = 2;
		flashcard.anchor = GridBagConstraints.CENTER;
		add(flashcardB,flashcard);
		
		/*
		GridBagConstraints matchgame = new GridBagConstraints();
		matchgame.gridx = 2;
		matchgame.gridy = 2;
		matchgame.gridwidth = 2;
		matchgame.gridheight = 1;
		matchgame.anchor = GridBagConstraints.CENTER;
		add(matchGameB,matchgame);
		*/
		
		GridBagConstraints ctest = new GridBagConstraints();
		ctest.ipady = 15;
		ctest.gridx = 1;
		ctest.gridy = 3;
		ctest.gridwidth = 2;
		ctest.gridheight = 2;
		ctest.anchor = GridBagConstraints.CENTER;
		add(cTestB,ctest);
		
		GridBagConstraints etest = new GridBagConstraints();
		etest.ipady = 15;
		etest.gridx = 1;
		etest.gridy = 5;
		etest.gridwidth = 2;
		etest.gridheight = 2;
		etest.anchor = GridBagConstraints.CENTER;
		add(eTestB,etest);
	}
}
