
public class QuizWord extends Word{
	private boolean answer;
	
	public QuizWord(Word word) {
		super(word);
		answer=false;
	}
	public void setAnswer() {
		answer=true;
	}
	public boolean getAnswer() {
		return answer;
	}
}
