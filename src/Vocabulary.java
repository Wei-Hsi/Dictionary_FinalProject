public class Vocabulary {
	
	private String POS;
	private String Cword;
	
	public Vocabulary(String POS, String Cword) {
		// TODO Auto-generated constructor stub
		this.POS = POS;
		this.Cword = Cword;
	}
	
	public String getPOS() {
		return POS;
	}
	
	public String getCword() {
		return Cword;
	}
	
	public void setPOS(String pOS) {
		POS = pOS;
	}
	
	public void setCword(String cword) {
		Cword = cword;
	}
}
