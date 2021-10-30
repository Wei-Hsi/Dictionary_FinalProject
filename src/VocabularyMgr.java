import java.util.ArrayList;

public class VocabularyMgr {
	
	private ArrayList<Vocabulary> vocabularies;
	
	
	public VocabularyMgr() {
		// TODO Auto-generated constructor stub
		vocabularies = new ArrayList<Vocabulary>();
	}
	
	public void addVocabulary(String POS, String Cword) {
		// TODO Auto-generated method stub
		Vocabulary vocabulary = new Vocabulary(POS, Cword);
		vocabularies.add(vocabulary);
	}
	
	public ArrayList<Vocabulary> getVocabularies() {
		return this.vocabularies;
	}
}
