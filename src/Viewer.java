import java.sql.SQLException;

import javax.swing.JFrame;

public class Viewer {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		//SearchResultPanel panel = new SearchResultPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}