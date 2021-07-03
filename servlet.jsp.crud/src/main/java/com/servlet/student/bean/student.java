package com.servlet.student.bean;



public class student
{
	
	
	public int  student_No;
	public String  student_Name;
	public String  student_Dob;
	public String student_Doj;
	
	


	public int getStudent_No() {
		return student_No;
	}
	public void setStudent_No(int student_No) {
		this.student_No = student_No;
	}
	public String getStudent_Name() {
		return student_Name;
	}
	public void setStudent_Name(String student_Name) {
		this.student_Name = student_Name;
	}
	public String getStudent_Dob() {
		return student_Dob;
	}
	public void setStudent_Dob(String student_Dob) {
		this.student_Dob = student_Dob;
	}
	public String getStudent_Doj() {
		return student_Doj;
	}
	public void setStudent_Doj(String student_Doj) {
		this.student_Doj = student_Doj;
	}
	
	
	public student(int student_No, String student_Name, String student_Dob, String student_Doj) {
		super();
		this.student_No = student_No;
		this.student_Name = student_Name;
		this.student_Dob = student_Dob;
		this.student_Doj = student_Doj;
	}
	public student(String student_Name, String student_Dob, String student_Doj) {
		super();
		this.student_Name = student_Name;
		this.student_Dob = student_Dob;
		this.student_Doj = student_Doj;
	}

	
	
	
	
	
	
	
	

}
