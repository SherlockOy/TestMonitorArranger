package com.sherlock_wjj.monitor;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;




public class UserInterface {
	/**
	 * GUI class
	 *
	 */
	private JFrame mainFrame = new JFrame("国际关系学院考务安排系统");
	private JPanel mainSouthPanel = new JPanel();
	private JPanel mainCentralPanel =  new JPanel();
	private CardLayout card = new CardLayout();
	private JPanel inputPanel = new JPanel();
	private JPanel outputPanel = new JPanel();
	
	private JButton buttonSelectDir = new JButton("打开");
	private JTextField tf = new JTextField(40);
	
	private JButton buttonOK = new JButton("确认");
	private JButton buttonCancel = new JButton("退出");
	
	private JButton btnReturnInputInterface = new JButton("返回");
	private JButton btnOutputData = new JButton("导出");

	private DefaultTableModel monitorTM = new DefaultTableModel();
	private DefaultTableModel eiTM = new DefaultTableModel();
	private DefaultTableModel outputTM = new DefaultTableModel();
	
	ArrayList<Monitor> mntrArrList = new ArrayList<Monitor>();
	ArrayList<ExamItem> eiArrList = new ArrayList<ExamItem>();
	
	class XlsFileFilter extends FileFilter {

		@Override
		public boolean accept(File f) {
			//jxl is unable to handle .xlsx .xlsb .xlsm .csv files??
			if (f.getName().endsWith(".xls") || f.isDirectory())
			{
				return true;
			}else return false;
		}
		
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return "Excel工作表文件（.xls）";
		}
		
	}

	public void showInputInterface(){
		buttonOK.setVisible(true);
		buttonCancel.setVisible(true);
		btnReturnInputInterface.setVisible(false);
		btnOutputData.setVisible(false);
		
		card.show(mainCentralPanel, "input");
		
		mainSouthPanel.validate();
	}

	public void showOutputInterface(){
		card.show(mainCentralPanel, "output");
		buttonOK.setVisible(false);
		buttonCancel.setVisible(false);
		btnReturnInputInterface.setVisible(true);
		btnOutputData.setVisible(true);
		mainSouthPanel.validate();		
	}
	
	public void refreshInputScrollPane(){
		//更新InputScrollpanel里的数据
		
		//丢弃所有原来的数据
		monitorTM.setRowCount(0);
		eiTM.setRowCount(0);
		
		/*mntrArrList = IOperations.readXls2MonitorList();
		eiArrList = IOperations.readXls2ExamItemList();*/
		IOperations.readXls2MonitorList( mntrArrList );
		IOperations.readXls2ExamItemList(eiArrList);
		
		for(Monitor mntr: mntrArrList) {
			Vector<String> rowDataVector = new Vector<String>();
			rowDataVector.add(mntr.getWorkNumber());
			rowDataVector.add(mntr.getName());
			rowDataVector.add(mntr.getDepartment());
			rowDataVector.add(mntr.getTimeLeft() + "");
			monitorTM.addRow(rowDataVector);
		}
		
		for(ExamItem ei: eiArrList) {
			Vector<String> rowDataVector = new Vector<String>();
			rowDataVector.add(ei.getKey());
			rowDataVector.add(ei.getCourseName());
			rowDataVector.add(ei.getDepartment());
			rowDataVector.add(ei.getCategory());
			rowDataVector.add(ei.getExamRoom());
			rowDataVector.add(ei.getMonitorCount() + "");
			
			eiTM.addRow(rowDataVector);
		}
	}
	
	public void refreshOutputScrollPane(ArrayList<ExamItem> eiResultArrList){
		//更新OutputScrollpanel里的数据
		
		//丢弃所有原来的数据
		outputTM.setRowCount(0);
		
		for(ExamItem ei: eiResultArrList) {
			//判断当前的列数是否足够输出那么多个老师，
			//如果不足，调用autoAddOutputColumn来添加列数
			if ( outputTM.getColumnCount() - 6 < ei.getResultMonitorArrList().size()){
				int newColumnCount = ei.getResultMonitorArrList().size() - (outputTM.getColumnCount() - 6);
				autoAddOutputColumn(outputTM, newColumnCount);
			}
			
			Vector<String> rowDataVector = new Vector<String>();
			rowDataVector.add(ei.getKey());
			rowDataVector.add(ei.getCourseName());
			rowDataVector.add(ei.getDepartment());
			rowDataVector.add(ei.getCategory());
			rowDataVector.add(ei.getExamRoom());
			rowDataVector.add(ei.getMonitorCount() + "");
			for(Monitor mntr: ei.getResultMonitorArrList()){
				rowDataVector.add(mntr.getName());
			}			
				
			outputTM.addRow(rowDataVector);
		}
	}
	
	//JAVA传递对象的时候传递的是引用中存放的地址，所以不用回传，
	//只要不改变方法中形参引用存放的地址（不改变，指的是不用new，或者用其他的引用给它赋值），
	//方法中用形参来操作同样会改变实参引用的所指向的堆内存部分	
	public void autoAddOutputColumn(DefaultTableModel outputTM, int newColumnCount){
		int initialIndex = outputTM.getColumnCount() - 6 + 1;
		for(int i = 0; i < newColumnCount ; i++ ){
			int mntrIndex = initialIndex;
			String columnName = new String("监考老师" + mntrIndex);
			outputTM.addColumn(columnName);
			initialIndex++;
		}
		
	}
	
	public void showMainFrame(){
		mainFrame.setLayout(new BorderLayout());
		inputPanel.setLayout(new BorderLayout());
		outputPanel.setLayout(new BorderLayout());
		mainCentralPanel.setLayout(card);
		mainCentralPanel.add(inputPanel,"input");
		mainCentralPanel.add(outputPanel,"output");
		
		
		//***********mainSouthPanel****************
		mainSouthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		mainSouthPanel.add(buttonOK);				
		mainSouthPanel.add(btnReturnInputInterface);
		mainSouthPanel.add(buttonCancel);
		mainSouthPanel.add(btnOutputData);
		btnReturnInputInterface.setVisible(false);
		btnOutputData.setVisible(false);
		
		buttonOK.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				//**
				//*Here makes the function call for ArrangeRobot to arrange. 
				//**
				
				eiArrList.get(0).addToResultMonitorArrList(mntrArrList.get(0));
				eiArrList.get(0).addToResultMonitorArrList(mntrArrList.get(1));
				eiArrList.get(0).addToResultMonitorArrList(mntrArrList.get(2));
				eiArrList.get(0).addToResultMonitorArrList(mntrArrList.get(3));
				
				try{
					refreshOutputScrollPane(eiArrList);
					showOutputInterface();
				} catch (NullPointerException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "请先选择要导入的文件", "提示", JOptionPane.YES_NO_OPTION);
				} 
			}
		});
		
		buttonCancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				mainFrame.dispose();
				System.exit(0);
			}
		});
		
		btnReturnInputInterface.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				showInputInterface();
			}
		});
		

		
		btnOutputData.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Here calls the IOperations function to write the result set to a xls.
				
				IOperations.writeResult2Xls(eiArrList, outputTM.getColumnCount());
				//For Debug
				//IOperations.writeResult2Xls(10);
			}
		});
		
		
		//***********InputPanel****************
		JPanel inNorthPanel = new JPanel();
		inNorthPanel.add(tf);
		inNorthPanel.add(buttonSelectDir);
		inNorthPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		inputPanel.add(inNorthPanel,BorderLayout.NORTH);

		buttonSelectDir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JFrame jf4fc = new JFrame("国际关系学院考务安排系统");
				JFileChooser fc = new JFileChooser(".");
				XlsFileFilter xff = new XlsFileFilter(); 
				fc.setFileFilter(xff);
				fc.showDialog(jf4fc,"选择要导入的文件");
				//获取被选择的文件, 更新TextField, 传值给IOperations, 
				tf.setText(fc.getSelectedFile().getPath());
				IOperations.setXlsFile(fc.getSelectedFile());
				IOperations.readXls2MonitorList(mntrArrList);
				IOperations.readXls2ExamItemList(eiArrList);
				//这里在调用IO，刷新界面下方的表格显示
				refreshInputScrollPane();
				inputPanel.validate();
			}
		});

		
		//===============ExamItem Table=============
		Object[] inputExamItemTableColName = {"课程编号", "课程名称", "开课系", "课程属性", "考场", "所需监考老师数"};
		eiTM.setDataVector(null, inputExamItemTableColName);
		JTable examItemTable = new JTable(eiTM);
		JScrollPane centerExamScrollPane = new JScrollPane(examItemTable);
		
		//===============Monitor Table=============
		Object[] inputMonitorTableColName = {"教室编号", "姓名", "系别", "可监考次数"};
		monitorTM.setDataVector(null, inputMonitorTableColName);
		JTable monitorTable = new JTable(monitorTM);
		JScrollPane centerMonitorScrollPane = new JScrollPane(monitorTable);
		
		//===============TabbedPane=============
		JTabbedPane centerTabbedPane = new JTabbedPane();
		centerTabbedPane.add("监考老师信息表", centerMonitorScrollPane);
		centerTabbedPane.add("考试信息表", centerExamScrollPane);
		//ScrollPane绝对不能用add来添加东西，只能用构造函数的时候添加。。搞了一整天
		//错错错错错centerExamScrollPane.add(inputTable);
		//错错错错错centerExamScrollPane.add(examItemTable);
		//centerExamScrollPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
		centerTabbedPane.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
		//inputPanel.add(centerExamScrollPane,BorderLayout.CENTER);
		inputPanel.add(centerTabbedPane,BorderLayout.CENTER);
		
		
		//*****************Output Panel*******************

		Object[] columnTitle2 = {"课程编号", "课程名称", "开课系", "课程属性", "考场", "所需老师数", "监考老师1"};
		outputTM.setDataVector(null, columnTitle2);
		JTable outputtable = new JTable(outputTM);
		JScrollPane outCenterScrollPane = new JScrollPane(outputtable);
		outCenterScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		outCenterScrollPane.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
		
		outputPanel.add(outCenterScrollPane,BorderLayout.CENTER);
		
		mainFrame.add(mainCentralPanel, BorderLayout.CENTER);
		mainFrame.add(mainSouthPanel, BorderLayout.SOUTH);
		
		//********Show Window**********
		Dimension dm = new Dimension(800,640);
		mainFrame.setPreferredSize(dm);
		//set the window unresizable
		//mainFrame.setResizable(false);
		//set minimum size for the window
		mainFrame.setMinimumSize(dm);
		mainFrame.pack();
		//Place the Frame in the center of screen
		mainFrame.setLocationRelativeTo(null);
		//Set default close operation as exit, the default value is DISPOSE_ON_CLOSE
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
	}



}