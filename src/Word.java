import java.util.ArrayList;

public class Word {
	private String english;
	private ArrayList<String>pos,chinese;
	
	public Word(String english) {
		this.english=english;
		this.pos=new ArrayList<String>();
		this.chinese=new ArrayList<String>();
	}
	public Word(Word word) {
		this.english=word.getEnglish();
		this.pos=word.getPOS();
		this.chinese=word.getChinese();
	}
	
	public String getEnglish() {
		return english;
	}
	public ArrayList<String> getPOS() {
		return pos;
	}
	
	public String getPOS(int n) {
		return pos.get(n);
	}
	
	public ArrayList<String> getChinese(){
		return chinese;
	}
	
	public String getChinese(int n) {
		return chinese.get(n);
	}
	
	public void addPOS(String s) {
		pos.add(s);
	}
	
	public void addChinese(String s) {
		chinese.add(s);
	}
}
