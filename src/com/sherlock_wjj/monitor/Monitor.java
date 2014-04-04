package com.sherlock_wjj.monitor;


public class Monitor {

	String workNumber;
	String name;
	String department;
	int timeLeft;
	
	Monitor(){
		
	}
	
	Monitor(String wn, String name, String dept, int tl){
		this.setWorkNumber(wn);
		this.setName(name);
		this.setDepartment(dept);
		this.setTimeLeft(tl);
	}
	
	public void setWorkNumber(String wn){
		this.workNumber = wn;
	}
	
	public String getWorkNumber(){
		return this.workNumber;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setDepartment(String dept){
		this.department = dept;
	}
	
	public String getDepartment(){
		return this.department;
	}
	
	public void setTimeLeft(int tl){
		this.timeLeft = tl;
	}
	
	public int getTimeLeft(){
		return this.timeLeft;
	}
	

}
