package com.listen;

import java.awt.Color;
import java.awt.Component;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import com.ManageWindows.CollegeMan;
import com.ManageWindows.Course;
import com.tools.Tools;
import com.windows.ManageWindows;

public class ItemListenMouse implements MouseListener {

	Object obj;
	JLayeredPane jPanel3;

	public static JLabel oldJLabel;//记录新点击的
	boolean sta;
	
	public ItemListenMouse(JLayeredPane jPanel3,Object obj) {
		this.obj=obj;
		this.jPanel3=jPanel3;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("鼠标释放了");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		JLabel jLabel=(JLabel)e.getSource();
		sta=(jLabel==oldJLabel);
		
		if(sta!=true) {
			jPanel3.moveToFront((Component) obj);//将盘子放到第一层
			jLabel.setForeground(new Color(30,144,255));
			oldJLabel.setForeground(new Color(51,51,51));
			oldJLabel=jLabel;
			
			
	
		
		}
		
		
		
		
		
	
	
	
	
		
		
		
			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("鼠标离开了");
	}
		
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jLabel=(JLabel)e.getSource();
		
		
		sta=(jLabel==oldJLabel);
		if(sta!=true) {
			jLabel.setForeground(new Color(30,144,255));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jLabel=(JLabel)e.getSource();
		
		sta=(jLabel==oldJLabel);
		if(sta!=true) {
			jLabel.setForeground(new Color(51,51,51));
		}
		
	
		
	
		
	
		
	}

}
