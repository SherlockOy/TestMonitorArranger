package com.sherlock_wjj.monitor;

import java.io.File;

import jxl.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.Number;

//import java.util.ArrayList;

public class IOperations {

	static File xlsfile = null;
	/*static ArrayList<Monitor> mntrarrlist = new ArrayList<Monitor>();
	static ArrayList<ExamItem> eiarrlist = new ArrayList<ExamItem>();*/
	
	
	public static void setXlsFile (File f) {
		xlsfile = f;
		
	}
	
	public static void readXls2MonitorList (ArrayList<Monitor> mArrList) {
		try {
			
			Workbook wk = Workbook.getWorkbook(xlsfile);
			Sheet sheet = wk.getSheet(0);
			int countRow = sheet.getRows();
			
			if( ! mArrList.isEmpty() ) {
				mArrList.removeAll(mArrList);
			}
			
			for(int i = 0; i <= countRow-1; i++) {
				String wn = sheet.getCell(0, i).getContents();
				String name = sheet.getCell(1, i).getContents();
				String dept = sheet.getCell(2, i).getContents();
				int tl = Integer.parseInt(sheet.getCell(3, i).getContents()); 
				mArrList.add(new Monitor(wn, name, dept, tl));
			}
			
			/*******For Debug********
			for(Monitor mntr: mArrList){
				System.out.println(mntr.getWorkNumber() + " - "
								  	+ mntr.getName() + " - "
								  	+ mntr.getDepartment() + " - "
								  	+ mntr.getTimeLeft());
			}
			**************************/
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件导入时出现错误", 
					"发生错误", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件打开错误，请选择正确的文件", 
					"发生错误", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public static void readXls2ExamItemList(ArrayList<ExamItem> eiAList) {
		
		try {
			Workbook wk = Workbook.getWorkbook(xlsfile);
			Sheet sheet = wk.getSheet(1);
			int countRow = sheet.getRows();
			
			if( ! eiAList.isEmpty() ) {
				eiAList.removeAll(eiAList);
			}
			
			for(int i = 0; i <= countRow-1; i++) {
				String cCode = sheet.getCell(0, i).getContents(); 
				String cn = sheet.getCell(1, i).getContents();
				String dept = sheet.getCell(2, i).getContents();
				String cat = sheet.getCell(3, i).getContents();
				String eRoom = sheet.getCell(4, i).getContents();
				int mntrcount = Integer.parseInt(sheet.getCell(5, i).getContents());
				eiAList.add(new ExamItem(cCode, cn, dept, cat, eRoom, mntrcount));
			}
			
			/*******For Debug********
			for(ExamItem ei: eiAList){
				System.out.println(ei.getKey() + " - "
								  	+ ei.getDepartment() + " - "
								  	+ ei.getMonitorCount() + " - "
								  	+ ei.getCategory()
								  );
			}
			**************************/
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件导入时出现错误", 
					"发生错误", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件打开错误，请选择正确的文件", 
					"发生错误", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * Write ExamItem to 监考安排表.xls
	 * 
	 * @param columnCount 
	 * 
	 */
	public static void writeResult2Xls(ArrayList<ExamItem> eiAList, int outputPanelTablecolumnCount){
		try {
			//write Result to xls.
			WritableWorkbook workbook = Workbook.createWorkbook(new File("监考安排表.xls"));
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			
			//打印表头
			Label labelKeyTiltle = new Label(0, 0, "考试编号"); 
			sheet.addCell(labelKeyTiltle);
			Label labelCourseNameTiltle = new Label(1, 0, "考试课程名称");
			sheet.addCell(labelCourseNameTiltle);
			Label labelDeptTiltle = new Label(2, 0, "开课系");
			sheet.addCell(labelDeptTiltle);
			Label labelCatTiltle = new Label(3, 0, "课程类别");
			sheet.addCell(labelCatTiltle);
			Label labelExamRoomTiltle = new Label(4, 0, "考场");
			sheet.addCell(labelExamRoomTiltle);
			Label labelMntrCountTiltle = new Label(5, 0, "所需监考老师数量");
			sheet.addCell(labelMntrCountTiltle);
			//打印监考老师表头
			if ( outputPanelTablecolumnCount > 6) {
				for(int i = 0; i < outputPanelTablecolumnCount - 6; i++){
					Label labelMonitorIndex = new Label(i+6, 0, "监考老师 - " + (i+1));
					sheet.addCell(labelMonitorIndex);
				}
			}
			
			//从第2行（index为1）开始输出，共输出eiarrlist.size() - 1  行
			for(int i = 1; i <= eiAList.size() ; i++ ) {
					Label labelKey = new Label(0, i, eiAList.get(i-1).getKey()); 
					sheet.addCell(labelKey);
					Label labelCourseName = new Label(1, i, eiAList.get(i-1).getCourseName());
					sheet.addCell(labelCourseName);
					Label labelDept = new Label(2, i, eiAList.get(i-1).getDepartment());
					sheet.addCell(labelDept);
					Label labelCat = new Label(3, i, eiAList.get(i-1).getCategory());
					sheet.addCell(labelCat);
					Label labelExamRoom = new Label(4, i, eiAList.get(i-1).getExamRoom());
					sheet.addCell(labelExamRoom);
					Number numberMntrCount = new Number(5, i, eiAList.get(i-1).getMonitorCount());
					sheet.addCell(numberMntrCount);
					//Here write the MonitorArrayList
					int j = 6;
					for(Monitor mntr: eiAList.get(i - 1).getResultMonitorArrList()){
						Label labelMonitorName = new Label(j++, i, mntr.getName());
						sheet.addCell(labelMonitorName);
					}
					
			}
			
			// All sheets and cells added. Now write out the workbook
			workbook.write(); 
			workbook.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件导出失败，请重试", 
											"发生错误", JOptionPane.ERROR_MESSAGE);
		} catch (WriteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "文件导出失败，请重试", 
											"发生错误", JOptionPane.ERROR_MESSAGE);
		}
	}


}
