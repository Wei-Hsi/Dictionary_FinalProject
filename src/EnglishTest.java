import java.sql.SQLException;
import java.util.ArrayList;

public class EnglishTest {
    private ArrayList<ArrayList<QuizWord>> quiz;
    private SQL sql;
    private ArrayList<Boolean> record;
    private String wordBank;
    public EnglishTest(String wordBank) throws SQLException {
    	this.wordBank=wordBank;
        quiz=new ArrayList<ArrayList<QuizWord>>();
        record=new ArrayList<Boolean>();
        sql=new SQL();
        for(int i=0;i<10;i++) {
            quiz.add(sql.createQuiz(wordBank));
            record.add(null);
        }
    }
    public ArrayList<ArrayList<QuizWord>> getQuiz() {
        return quiz;
    }
    public ArrayList<QuizWord> getQuiz(int n){
        return quiz.get(n);
    }

    public void setRecord(int n,boolean b) {
        record.set(n, b);
    }

    public int getScore() {
        int score=0;
        for(Boolean b:record) {
            if(b==true) {
                score++;
            }
        }
        return score;
    }
}

