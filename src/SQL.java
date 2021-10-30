import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQL {
	private static String url,username,password;
	private static Connection conn;
	
	public SQL() throws SQLException {
		String server="jdbc:mysql://140.119.19.73:9306/";
		String database="MG14";
		String config= "?useUnicode=true&characterEncoding=utf8";
		username="MG14";
		password="RHJX2R";
		url=server+database+config;
		initializing();
	}
	private void initializing() throws SQLException{
	conn = DriverManager.getConnection(url,username,password);
	}
	
	public Word randomWord(String wordBank) throws SQLException {
		if(wordBank.equals("7000word_1")) {
		String sql= "SELECT * FROM 7000word_1 "+
						" ORDER BY RAND()"
						+" LIMIT 1";
			Statement state=conn.prepareStatement(sql);
			ResultSet result = state.executeQuery(sql);
			String Eword="";
			result.next();
			Eword=result.getString("Eword");
			
			Word word=new Word(Eword);
			
			String sql1="SELECT * from 7000word_1 WHERE Eword=?";
			String pos="";
			String chinese="";
			PreparedStatement searchWordStat= conn.prepareStatement(sql1);
			searchWordStat.setString(1, Eword);
			ResultSet result1 = searchWordStat.executeQuery();
			while(result1.next()) {
				pos=result1.getString("POS");
				chinese=result1.getString("Cword");
				word.addPOS(pos);
				word.addChinese(chinese);
			}
			return word;
		}
		if(wordBank.equals("7000word_2")) {
			String sql= "SELECT * FROM 7000word_2 "+
							" ORDER BY RAND()"
							+" LIMIT 1";
				Statement state=conn.prepareStatement(sql);
				ResultSet result = state.executeQuery(sql);
				String Eword="";
				result.next();
				Eword=result.getString("Eword");
				
				Word word=new Word(Eword);
				
				String sql1="SELECT * from 7000word_2 WHERE Eword=?";
				String pos="";
				String chinese="";
				PreparedStatement searchWordStat= conn.prepareStatement(sql1);
				searchWordStat.setString(1, Eword);
				ResultSet result1 = searchWordStat.executeQuery();
				while(result1.next()) {
					pos=result1.getString("POS");
					chinese=result1.getString("Cword");
					word.addPOS(pos);
					word.addChinese(chinese);
				}
				return word;
			}
		if(wordBank.equals("Incorrect")) {
			String sql= "SELECT * FROM Incorrect "+
							" ORDER BY RAND()"
							+" LIMIT 1";
				Statement state=conn.prepareStatement(sql);
				ResultSet result = state.executeQuery(sql);
				String Eword="";
				result.next();
				Eword=result.getString("Eword");
				
				Word word=new Word(Eword);
				
				String sql1="SELECT * from Incorrect WHERE Eword=?";
				String pos="";
				String chinese="";
				PreparedStatement searchWordStat= conn.prepareStatement(sql1);
				searchWordStat.setString(1, Eword);
				ResultSet result1 = searchWordStat.executeQuery();
				while(result1.next()) {
					pos=result1.getString("POS");
					chinese=result1.getString("Cword");
					word.addPOS(pos);
					word.addChinese(chinese);
				}
				return word;
			}
		if(wordBank.equals("NewWord")) {
			String sql= "SELECT * FROM NewWord "+
							" ORDER BY RAND()"
							+" LIMIT 1";
				Statement state=conn.prepareStatement(sql);
				ResultSet result = state.executeQuery(sql);
				String Eword="";
				result.next();
				Eword=result.getString("Eword");
				
				Word word=new Word(Eword);
				
				String sql1="SELECT * from NewWord WHERE Eword=?";
				String pos="";
				String chinese="";
				PreparedStatement searchWordStat= conn.prepareStatement(sql1);
				searchWordStat.setString(1, Eword);
				ResultSet result1 = searchWordStat.executeQuery();
				while(result1.next()) {
					pos=result1.getString("POS");
					chinese=result1.getString("Cword");
					word.addPOS(pos);
					word.addChinese(chinese);
				}
				return word;
			}
		return null;
		
	}
	
	public ArrayList<QuizWord> randomFourWord(String wordBank) throws SQLException {
		Word word1,word2,word3,word4;
		word1=randomWord(wordBank);
		word2=randomWord(wordBank);
		word3=randomWord(wordBank);
		word4=randomWord(wordBank);
		while(word1.getEnglish().equals(word2.getEnglish())){
			word2=randomWord(wordBank);
		}
		while(  word1.getEnglish().equals(word3.getEnglish())||
				word2.getEnglish().equals(word3.getEnglish())  ){
			word3=randomWord(wordBank);
		}
		while(  word1.getEnglish().equals(word4.getEnglish())||
				word2.getEnglish().equals(word4.getEnglish())||
				word3.getEnglish().equals(word4.getEnglish())  ){
			word4=randomWord(wordBank);
		}
		QuizWord option1,option2,option3,option4;
		option1= new QuizWord(word1);
		option2= new QuizWord(word2);
		option3= new QuizWord(word3);
		option4= new QuizWord(word4);
		ArrayList<QuizWord> quiz=new ArrayList<QuizWord>();
		quiz.add(option1);
		quiz.add(option2);
		quiz.add(option3);
		quiz.add(option4);
		
		return quiz;
	}
	
	public ArrayList<QuizWord> createQuiz(String wordBank) throws SQLException{
		ArrayList<QuizWord> quiz=randomFourWord(wordBank);
		int rng=(int)Math.floor(Math.random()*4);
		switch(rng) {
		case 0: 
            quiz.get(0).setAnswer();
            break; 
        case 1: 
        	quiz.get(1).setAnswer();
            break; 
        case 2: 
        	quiz.get(2).setAnswer();
            break; 
        case 3: 
        	quiz.get(3).setAnswer();
            break; 
		}
		return quiz;
	}
	
	public String findEnglish(String Cword,String wordBank) throws SQLException {
		if(wordBank.equals("7000word_1")) {
		String sql="SELECT * from 7000word_1 WHERE Cword=?";
		String Eword="";
		PreparedStatement createTableStat= conn.prepareStatement(sql);
		createTableStat.setString(1, Cword);
		ResultSet result = createTableStat.executeQuery();
		result.next();
		Eword=result.getString("Eword");
		return Eword;}
		if(wordBank.equals("7000word_2")) {
			String sql="SELECT * from 7000word_2 WHERE Cword=?";
			String Eword="";
			PreparedStatement createTableStat= conn.prepareStatement(sql);
			createTableStat.setString(1, Cword);
			ResultSet result = createTableStat.executeQuery();
			result.next();
			Eword=result.getString("Eword");
			return Eword;}
		if(wordBank.equals("Incorrect")) {
			String sql="SELECT * from Incorrect WHERE Cword=?";
			String Eword="";
			PreparedStatement createTableStat= conn.prepareStatement(sql);
			createTableStat.setString(1, Cword);
			ResultSet result = createTableStat.executeQuery();
			result.next();
			Eword=result.getString("Eword");
			return Eword;}
		if(wordBank.equals("NewWord")) {
			String sql="SELECT * from NewWord WHERE Cword=?";
			String Eword="";
			PreparedStatement createTableStat= conn.prepareStatement(sql);
			createTableStat.setString(1, Cword);
			ResultSet result = createTableStat.executeQuery();
			result.next();
			Eword=result.getString("Eword");
			return Eword;}
		return null;
	}
	
	public void addToIncorrect(Word word) throws SQLException {
		String sql = "INSERT INTO Incorrect VALUES(?,?,?)";
		PreparedStatement addStat = conn.prepareStatement(sql);
		addStat.setString(1, word.getEnglish());
		addStat.setString(2, word.getPOS(0));
		addStat.setString(3, word.getChinese(0));
		addStat.executeUpdate();
	}
	public int returnWords(String wordBank) throws SQLException {
		int words=0;
		if(wordBank.equals("7000word_1")) {
			String sql="SELECT * from 7000word_1";
			Statement createTableStat= conn.prepareStatement(sql);
			ResultSet result = createTableStat.executeQuery(sql);
			while(result.next()) {
				words++;
			 }
		}
		if(wordBank.equals("7000word_2")) {
			String sql="SELECT * from 7000word_2";
			Statement createTableStat= conn.prepareStatement(sql);
			ResultSet result = createTableStat.executeQuery(sql);
			while(result.next()) {
				words++;
			 }
		}
		if(wordBank.equals("Incorrect")) {
			String sql="SELECT * from Incorrect";
			Statement createTableStat= conn.prepareStatement(sql);
			ResultSet result = createTableStat.executeQuery(sql);
			while(result.next()) {
				words++;
			 }
		}
		if(wordBank.equals("NewWord")) {
			String sql="SELECT * from NewWord";
			Statement createTableStat= conn.prepareStatement(sql);
			ResultSet result = createTableStat.executeQuery(sql);
			while(result.next()) {
				words++;
			 }
		}
		return words;
	}
	public void removeIncorrect(Word word) throws SQLException {
		String sql= "DELETE FROM Incorrect" +
					" WHERE Eword=?";
		PreparedStatement removeStat =conn.prepareStatement(sql);
		removeStat.setString(1,word.getEnglish());
		removeStat.executeUpdate();
	}
	public void removeIncorrect(String word) throws SQLException {
		String sql= "DELETE FROM Incorrect" +
					" WHERE Eword=?";
		PreparedStatement removeStat =conn.prepareStatement(sql);
		removeStat.setString(1,word);
		removeStat.executeUpdate();
	}
}
