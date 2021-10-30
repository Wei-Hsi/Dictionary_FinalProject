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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class SearchPanel extends JPanel{

	private static final int FIELD_WIDTH = 16;

	private final ExecSQL exec;
	private JPanel panel;
	private JPanel searchBarPanel; 
	private JTextField searchField; 
	private JButton searchButton;
	private JButton deleteButton;
	
	private String inputEword;
	private String inputPOS;
	private String inputCword;

	protected DefaultTableModel model = null;
	private JTable nodeTable = null;
	private String[][] datas = null; 
	private final String[] titlelist = {"Word", "Time"};
	
	public SearchPanel() throws SQLException {
		// TODO Auto-generated constructor stub
		this.exec = new ExecSQL();
		exec.createSearchingWordTable();
		
		setLayout(new BorderLayout());
		createsearchBarComp();
		createPanel();
	}

	public void initTable() throws SQLException {
		Map<String, String> map =  exec.getWord();
		Iterator<Entry<String, String>> itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, String> pair = itr.next();
			model.insertRow(0,new Object[] {pair.getKey(), pair.getValue()});
		}
	}
	
	private void createsearchBarComp() {
		// TODO Auto-generated method stub
		searchBarPanel = new JPanel();
		searchBarPanel.setBackground(new Color(22,74,144));
		
		searchField = new JTextField(FIELD_WIDTH);
		
		URL url = SearchPanel.class.getResource("/img/search.png");
		ImageIcon icon = new ImageIcon(url);
		icon.setImage(icon.getImage().getScaledInstance(15,15,Image.SCALE_SMOOTH));
		searchButton = new JButton(icon);
		searchButton.setBackground(new Color(238,185,5)); //���s�L�k�ܦ�
		
		deleteButton = new JButton("clear all");
		deleteButton.setBorder(null);
		deleteButton.setForeground(Color.WHITE);
		Font btnBaseLine = new Font("Helvetica",Font.PLAIN,14);
		deleteButton.setFont(btnBaseLine);
		
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//folderBtn.setBackground(Color.BLACK);
				try {
					deleteButton.setBackground(new Color(254,196,0));
					exec.clearAll();
					while(model.getRowCount() != 0) {
						model.removeRow(0);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		ActionListener listener = new ClickListener();
		deleteButton.addActionListener(listener);
		
		
		searchBarPanel.add(searchField);		
		searchBarPanel.add(searchButton);
		searchBarPanel.add(deleteButton);
	}
	
	public void addButtonListener(JPanel panel) {
		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					String word = searchField.getText();
					if(exec.findWord(word)) {
						exec.addSearchingWord(searchField.getText());

						SearchResultPanel searchResultPage = new SearchResultPanel(word, exec.getVocabularyMgr());
						searchResultPage.addButtonListener(panel);
	
						panel.add(searchResultPage, "2");
						CardLayout cardLayout = (CardLayout) panel.getLayout();
						cardLayout.show(panel, "2");
						searchField.setText("");
					}
					else {
						int selection = JOptionPane.showConfirmDialog(panel.getParent(), "The word is not in 7000_word folder!\nDo you want to add the new word?","Add new word",JOptionPane.OK_CANCEL_OPTION);
						if (selection == JOptionPane.OK_OPTION) {
							inputEword = JOptionPane.showInputDialog(panel.getParent(), "Please input the English word:");
							inputPOS = JOptionPane.showInputDialog(panel.getParent(), "Please input the part of the speech of the word:");
							inputCword = JOptionPane.showInputDialog(panel.getParent(), "Please input the Chinese meaning of the word:");
							exec.createNewWord(inputEword, inputPOS, inputCword);
							JOptionPane.showMessageDialog(panel.getParent(),"The word has been added to the 'NewWord' folder.");
						} else if (selection == JOptionPane.CANCEL_OPTION) {
							JOptionPane.showMessageDialog(panel.getParent(),"The word will not be added to the 'NewWord' folder.");
						}
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		ActionListener listener = new ClickListener();
		searchButton.addActionListener(listener);
	}
	private void createPanel() throws SQLException {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		model = new DefaultTableModel(datas, titlelist);
		nodeTable = new JTable(model);
		initTable();
		
		nodeTable.setGridColor(Color.GRAY);
		nodeTable.setRowHeight(30);
		nodeTable.setShowVerticalLines(false);
		nodeTable.setFont(new Font("DIALOG", Font.PLAIN, 13));
		nodeTable.getTableHeader().setReorderingAllowed(false);
		
		createsearchBarComp();
		
		panel.add(searchBarPanel,BorderLayout.NORTH);
		panel.add(new JScrollPane(nodeTable), BorderLayout.CENTER);
		add(panel,BorderLayout.CENTER);
	}
	public void addTableModelListener(JPanel panel) {
		model.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				try {
					if(nodeTable.getSelectedRowCount() == 1) {
						System.out.println(nodeTable.getSelectedRow());
						if(exec.findWord(nodeTable.getValueAt(nodeTable.getSelectedRow(), 0).toString())) {
							SearchResultPanel searchResultPage = new SearchResultPanel(nodeTable.getValueAt(nodeTable.getSelectedRow(), 0).toString(),
																		exec.getVocabularyMgr());
							searchResultPage.addButtonListener(panel);
							panel.add(searchResultPage, "2");
							CardLayout cardLayout = (CardLayout) panel.getLayout();
							cardLayout.show(panel, "2");
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
