import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExecSQL {
	public String POS;
	public String Cword;
	public Timestamp dateTime;
	
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn;
	private ResultSet result;
	private ResultSet result2;
	private ResultSet result3;
	
	private VocabularyMgr vocabularyMgr;
	
	public ExecSQL() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "MG14";
		String config = "?useUnicode=true&characterEncoding=utf8";
		this.url= server + database + config;
		this.username= "MG14";
		this.password= "RHJX2R";
		initializing();
	}
	
	private void initializing() throws SQLException {
		conn = DriverManager.getConnection(url,username,password);
	}
	//�ϥΪ̷j�M��r�ɡA�ݸ�Ʈw���S���o�Ӧr
	public boolean findWord(String word) throws SQLException {
		vocabularyMgr = new VocabularyMgr();
		
		String query = "SELECT * FROM 7000word_1 WHERE Eword=?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, word);
		result = stat.executeQuery();
		
		String query2 = "SELECT * FROM 7000word_2 WHERE Eword=?";
		PreparedStatement stat2 = conn.prepareStatement(query2);
		stat2.setString(1, word);
		result2 = stat2.executeQuery();
		
		String query3 = "SELECT * FROM NewWord WHERE Eword=?";
		PreparedStatement stat3 = conn.prepareStatement(query3);
		stat3.setString(1, word);
		result3 = stat3.executeQuery();
		
		while (result.next()) {
			vocabularyMgr.addVocabulary(result.getString("POS"), result.getString("Cword"));
		}
		while (result2.next()) {
			vocabularyMgr.addVocabulary(result2.getString("POS"), result2.getString("Cword"));
		}
		while (result3.next()) {
			vocabularyMgr.addVocabulary(result3.getString("POS"), result3.getString("Cword"));
		}
		if (vocabularyMgr.getVocabularies().isEmpty()) {
			return false;
		}
		/***
		for (Vocabulary vocabulary : vocabularyMgr.getVocabularies()) {
			System.out.println("vocabulary");
			System.out.println(vocabulary.getPOS() + " : " + vocabulary.getCword());
		}
		***/
		return true;
	}
	
	public VocabularyMgr getVocabularyMgr() {
		return this.vocabularyMgr;
	}
	public String getPOS() throws SQLException {
		return this.POS;
	}
	public String getCword() throws SQLException {
		return this.Cword;		
	}
	//�ϥΪ̷j�M��r�ɡA�Ыظ�ƪ�
	public boolean createSearchingWordTable() throws SQLException {
		String query = "CREATE TABLE IF NOT EXISTS SearchingWord"
						+ " (Word VARCHAR(20) NOT NULL,"
						+ " Time DATETIME NOT NULL)";
		PreparedStatement createTableStat = conn.prepareStatement(query);
		int result = createTableStat.executeUpdate();
		if (result == 0) {
			System.out.print("Create Table: operation success\n");
			return true;
		} else {
			System.out.print("Create Table: operation failed\n");
			return false;
		}
	}
	//��j�M�L����r�[�i�s�Ъ�table
	public boolean addSearchingWord(String word) throws SQLException {
		final LocalDateTime currentDateTime = LocalDateTime.now();
		String query = "INSERT INTO SearchingWord VALUES(?,?)";
		PreparedStatement createTableStat = conn.prepareStatement(query);
		createTableStat.setString(1, word);
		createTableStat.setTimestamp(2, Timestamp.valueOf(currentDateTime));
		int result = createTableStat.executeUpdate();
		if (result == 1) {
			System.out.print("INSERT INTO SearchingWord Table: operation success\n");
			return true;
		} else {
			System.out.print("INSERT INTO SearchingWord Table: operation failed\n");
			return false;
		}
	}
	
	public Map<String, String> getWord() throws SQLException {
		String count = "SELECT COUNT(*) FROM SearchingWord";
		PreparedStatement stat = conn.prepareStatement(count);
		result = stat.executeQuery();
		result.next();
		int rowCount = result.getInt(1);
		Map<String, String> map = new LinkedHashMap<String, String>(rowCount);
		
		String query = "SELECT * FROM SearchingWord";
		PreparedStatement stat1 = conn.prepareStatement(query);
		result = stat1.executeQuery();
		
		while (result.next()) {
			System.out.println(result.getString("Word") + " : " + result.getString("Time"));
			map.put(result.getString("Word"), result.getString("Time"));
		}
		System.out.println("================================");
		System.out.println("getWord()!!!");
		return map;
	}
	public String getDateTime(String word) throws SQLException {
		String query = "SELECT * FROM SearchingWord WHERE Word=?";
		PreparedStatement stat = conn.prepareStatement(query);
		stat.setString(1, word);
		result = stat.executeQuery();
		
		while (result.next()) {
			dateTime = result.getTimestamp("Time");
		}
		return dateTime.toString();
	}
	//�M�����v����
	public boolean clearAll() throws SQLException {
		String query = "DELETE FROM SearchingWord";
		PreparedStatement createTableStat = conn.prepareStatement(query);
		int result = createTableStat.executeUpdate();
		if (result != 0) {
			System.out.print("Delete Records: operation success\n");
			return true;
		} else {
			System.out.print("Delete Records: operation failed\n");
			return false;
		}
	}
	//��d�쪺��r�[�i��ƪ��
	public boolean addNewWord(String Eword, String POS, String Cword) throws SQLException {
		String query = "INSERT INTO NewWord VALUES(?,?,?)";
		PreparedStatement createTableStat = conn.prepareStatement(query);
		createTableStat.setString(1, Eword);
		createTableStat.setString(2, POS);
		createTableStat.setString(3, Cword);
		int result = createTableStat.executeUpdate();
		if (result >= 1) {
			System.out.print("INSERT INTO NewWord Table: operation success\n");
			return true;
		} else {
			System.out.print("INSERT INTO NewWord Table: operation failed\n");
			return false;
		}
	}
	//�ۤv�إ߷s��r
	public boolean createNewWord(String Eword, String POS, String Cword) throws SQLException {
		String query = "INSERT INTO NewWord VALUES(?,?,?)";
		PreparedStatement createTableStat = conn.prepareStatement(query);
		createTableStat.setString(1, Eword);
		createTableStat.setString(2, POS);
		createTableStat.setString(3, Cword);
		int result = createTableStat.executeUpdate();
		if (result == 1) {
			System.out.print("INSERT INTO NewWord Table: operation success\n");
			return true;
		} else {
			System.out.print("INSERT INTO NewWord Table: operation failed\n");
			return false;
		}
	}
}
