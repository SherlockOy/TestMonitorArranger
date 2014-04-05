package com.sherlock_wjj.monitor;

import java.util.ArrayList;

public class ExamItem{
	/**
	 * 
	 */
	String key; // courseNumber + examRoomName
	String courseCode;//课程号
	String coursename;//课程名
	String department;//开课系、开课部门
	String category;//课程属性
	String ExamRoom;//考场
	int monitorCount;//所需监考老师
	ArrayList<Monitor> resultMonitorArrList = new ArrayList<Monitor>();
	
	ExamItem(){
		
	}
	
	ExamItem(String cCode, String cn, String dept, String cat, String eRoom, int mntrcount){
		this.setCourseCode(cCode);
		this.setCourseName(cn);
		this.setDepartment(dept);
		this.setCategory(cat);
		this.setExamRoom(eRoom);
		this.setMonitorCount(mntrcount);
		this.setKey(key);
	}
	
	public ArrayList<Monitor> getResultMonitorArrList() {
		return resultMonitorArrList;
	}

	public void addToResultMonitorArrList(Monitor mntr) {
		this.resultMonitorArrList.add(mntr);
	}
	
	public void setKey(String key){
		this.key = this.getCourseCode() + " - " + this.getExamRoom();
	}
	
	public String getKey(){
		return this.key;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseCode() {
		return this.courseCode;
	}

	public void setCourseName(String cn){
		this.coursename = cn;
	}
	
	public String getCourseName(){
		return this.coursename;
	}
	
	public void setDepartment(String dept){
		this.department = dept;
	}
	
	public String getDepartment(){
		return this.department;
	}
	
	public void setCategory(String cat){
		this.category = cat;
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public String getExamRoom() {
		return this.ExamRoom;
	}

	public void setExamRoom(String examRoom) {
		ExamRoom = examRoom;
	}
	
	public void setMonitorCount(int mntrcount){
		this.monitorCount = mntrcount;
	}
	
	public int getMonitorCount(){
		return this.monitorCount;
	}
	
	
	
}