package kr.or.dgit.bigdata.erp.dto;

public class Title {

	private int tcode;
	private String tname;

	public Title() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Title(int tcode, String tname) {
		super();
		this.tcode = tcode;
		this.tname = tname;
	}

	public int getTcode() {
		return tcode;
	}

	public void setTcode(int tcode) {
		this.tcode = tcode;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Title(int tcode) {
		super();
		this.tcode = tcode;
	}

	public Title(String tname) {
		super();
		this.tname = tname;
	}

}
