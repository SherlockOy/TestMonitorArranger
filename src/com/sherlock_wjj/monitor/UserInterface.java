package com.sherlock_wjj.monitor;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;



public class UserInterface{
	/**
	 * GUI class
	 *
	 */
	JFrame mainFrame = new JFrame("国际关系学院考务安排系统");
	final JPanel inputPanel = new JPanel();
	final JPanel outputPanel = new JPanel();
	
	JButton buttonSelectDir = new JButton("打开");
	JTextField ta = new JTextField(40);
	
	JButton buttonOK = new JButton("确认");
	JButton buttonCancel = new JButton("退出");
	
	JButton btnReturnInputInterface = new JButton("返回");
	JButton btnOutputData = new JButton("导出");
	


	public void showMainFrame(){
		//showInputInterface();
		/*showOutputInterface();
		
		mainFrame.add(outputPanel);
		inputPanel.setVisible(false);
		outputPanel.setVisible(false);*/
		
		showInputInterface();
		//inputPanel.setVisible(false);
		//showOutputInterface();
		//outputPanel.setVisible(false);
		
		//mainFrame.add(outputPanel, BorderLayout.CENTER);
		//mainFrame.add(inputPanel, BorderLayout.CENTER);
		//mainFrame.setComponentZOrder(inputPanel, 0);
		//inputPanel.setVisible(true);
		
		
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

	public void showInputInterface(){
		mainFrame.add(inputPanel, BorderLayout.CENTER);
		inputPanel.setLayout(new BorderLayout());
		
		
		JPanel northPanel = new JPanel();
		northPanel.add(ta);
		northPanel.add(buttonSelectDir);
		inputPanel.add(northPanel,BorderLayout.NORTH);
		//buttonSelectDir.setBackground(new Color(250,227,113));
		//northPanel.setBackground(new Color(250,227,113));
		//ta.setBackground(new Color(250,227,113));
		buttonSelectDir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JFrame jf4fc = new JFrame("国际关系学院考务安排系统");
				JFileChooser fc = new JFileChooser(".");
				fc.showDialog(jf4fc,"选择要导入的文件");
			}
		});
		
		
		
		//**Configure south area in frame 
		//*buttonOK, buttonCancel, and their action listener
		//**
		JPanel southRightPanel = new JPanel();
		southRightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		southRightPanel.add(buttonOK);
		southRightPanel.add(buttonCancel);
		//buttonOK.setBackground(new Color(250,227,113));
		//buttonCancel.setBackground(new Color(250,227,113));
		//southRightPanel.setBackground(new Color(250,227,113));
		inputPanel.add(southRightPanel,BorderLayout.SOUTH);
		buttonOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				/*int response=JOptionPane.showConfirmDialog(mainFrame, "是否确认开始安排监考", "提示", JOptionPane.OK_CANCEL_OPTION);
				if (response == JOptionPane.OK_OPTION){
					//**
					//*Here makes the function call for ArrangeRobot to arrange. 
					//**
					showOutputInterface();
					
				}else if(response == JOptionPane.CANCEL_OPTION){
					JOptionPane.showMessageDialog(null,"您按下了取消按钮");
				}*/
				showOutputInterface();
			}
		});
		buttonCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				mainFrame.dispose();
				System.exit(0);
			}
		});
		
		//**
		//*Configure the central JTabbedPane
		//*to display Monitors and ExamItem Data
		//**
		Object[][] tableData = 
			{
				new Object[]{"0001", "李", "信科系", 2},
				new Object[]{"0002", "苏", "公管系", 3},
				new Object[]{"0003", "李", "国经系", 1},
				new Object[]{"0004", "江", "英语系", 3},
				new Object[]{"0005", "王", "日法系", 2}
			};

		Object[] columnTitle = {"教师编号", "姓名", "系别", "可监考次数"};

		JTable table = new JTable(tableData , columnTitle);
		JScrollPane centerScrollPane = new JScrollPane(table);
		//centerScrollPane.setBackground(new Color(250,227,113));
		inputPanel.add(centerScrollPane,BorderLayout.CENTER);
		
		/*inputPanel.setVisible(true);
		outputPanel.setVisible(false);*/
		mainFrame.validate();
	}

	
	public void showOutputInterface(){
		mainFrame.remove(inputPanel);
		mainFrame.add(outputPanel, BorderLayout.CENTER);
		//inputPanel.setVisible(false);
		
		outputPanel.setLayout(new BorderLayout());
		JPanel outSouthPanel = new JPanel();
		//outSouthPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		outSouthPanel.add(btnReturnInputInterface);
		outSouthPanel.add(btnOutputData);
		
		btnReturnInputInterface.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				showInputInterface();
			}
		});
		
		Object[][] tableData2 = 
			{
				new Object[]{"c0001", "高数", "教学楼209", "信科系", "信科系",},
				new Object[]{"c0002", "线代", "教学楼331", "公管系", "信科系",},
				new Object[]{"c0003", "国关史", "教学楼205", "国经系", "信科系",},
				new Object[]{"c0004", "精读", "学交1放", "英语系", "信科系",},
				new Object[]{"c0005", "俄语", "学交语音教室", "日法系", "信科系",}
			};

		Object[] columnTitle2 = {"课程编号", "课程名称", "考场", "开课系", "监考老师1	"};

		JTable outputtable = new JTable(tableData2 , columnTitle2);
		JScrollPane outCenterScrollPane = new JScrollPane(outputtable);
		
		outputPanel.add(outCenterScrollPane,BorderLayout.CENTER);
		outputPanel.add(outSouthPanel, BorderLayout.SOUTH);
		
		/*inputPanel.setVisible(false);
		outputPanel.setVisible(true);*/
		mainFrame.validate();
	}

}