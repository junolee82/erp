package kr.or.dgit.bigdata.erp.dto;

public class Department {
	private int dcode;
	private String dname;
	private int floor;
	public Department(int dcode, String dname, int floor) {
		super();
		this.dcode = dcode;
		this.dname = dname;
		this.floor = floor;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDcode() {
		return dcode;
	}
	public void setDcode(int dcode) {
		this.dcode = dcode;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Department(String dname) {
		super();
		this.dname = dname;
	}

	
	

}
