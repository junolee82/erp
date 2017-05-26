package kr.or.dgit.bigdata.erp.dto;

import java.util.Date;

public class Employee {
	private int eno;
	private String ename;
	private int salary;
	private Department dno;
	private int gender;
	private Date joindate;
	private Title title;



	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", ename=" + ename + ", salary=" + salary + ", dno=" + dno + ", gender="
				+ gender + ", joindate=" + joindate + ", title=" + title + "]";
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEno() {
		return eno;
	}

	public void setEno(int eno) {
		this.eno = eno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Department getDno() {
		return dno;
	}

	public void setDno(Department dno) {
		this.dno = dno;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Employee(int eno, String ename, int salary, Department dno, int gender, Date joindate, Title title) {
		super();
		this.eno = eno;
		this.ename = ename;
		this.salary = salary;
		this.dno = dno;
		this.gender = gender;
		this.joindate = joindate;
		this.title = title;
	}


}
