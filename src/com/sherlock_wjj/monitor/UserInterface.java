package com.sherlock_wjj.monitor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UserInterface{
	/**
	 * GUI class
	 *
	 */
	private JFrame jFrame = new JFrame("主界面");
	private JPanel panel = new JPanel();
	private JButton buttonOK = new JButton("确认");
	private JButton buttonCancel = new JButton("退出");
		
	public void showMainFrame(){
		
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.panel.add(this.buttonOK);
		this.panel.add(this.buttonCancel);
		this.jFrame.add(this.panel);
		this.jFrame.setSize(320, 240);
		this.jFrame.setLocation(width / 2 - 160, height / 2 - 120);
		this.jFrame.setVisible(true);
		
		
		this.jFrame.addWindowListener( new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e){
		    	System.exit(1);
		    }
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		});
}

	public void reDrawInterface() {
		//Refresh GUI for Data Export
	}

}