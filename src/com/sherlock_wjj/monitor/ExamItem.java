package com.sherlock_wjj.monitor;

import java.util.ArrayList;

public class ExamItem{
	/**
	 * 
	 */
	String key; // courseNumber + examRoomName
	String department;
	int monitorCount;
	String category;
	ArrayList<Monitor> monitors = new ArrayList<Monitor>();

	public ExamItem(){
		
	}
	

}