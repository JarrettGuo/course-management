package com.tools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.management.DynamicMBean;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Tools {

	
	//设置窗口居中
	public static void setWindowPos(int WIDTH,int HEIGHT,JFrame jframe) {
		//传递过来宽和高
		   //设置成静态可以随时随地调用
        //设置窗口的位置以及大小  将窗口传递过来
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
        int width=screenSize.width;
        int height=screenSize.height;//取宽度和高度
        int x=(width-WIDTH)/2;
        int y=(height-HEIGHT)/2;
        jframe.setBounds(x, y, WIDTH, HEIGHT);
	}
	//获取密码
	public static String getPassword(JPasswordField jp) {
		String password=new String(jp.getPassword());
		return password;
	}
	//弹窗
	public static void messageWindows(String msg,String title) {
		JOptionPane.showMessageDialog(null,  msg, title,JOptionPane.WARNING_MESSAGE);
	}
	public static void messageWindows(String msg) {
		JOptionPane.showMessageDialog(null,  msg,"消息",JOptionPane.WARNING_MESSAGE);
	}
//__________________________________________________________________________________________
	
	//判读rs当中是否有数据
	public static boolean isHasData (ResultSet rs) {
		int num=0;
		try {
			
			while(rs.next()) {
				num++;
			}
			if(num==0) {
				rs.close();
				return false;
			}else {
				rs.close();
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		
			return false;
			
			
		}
	}
	//更新下拉框
	public static void upDateComboBox(String strsql,JComboBox jComboBox,String str,String prams) {//数据库语句 第二个是要更新的下拉框
		
		ResultSet rs = Mysqld.QueryData(strsql, null);
		jComboBox.removeAllItems();
		jComboBox.addItem("--"+str+"--");
		try {
			int j=0;
			while(rs.next()) {
				String name=rs.getString(prams);
				jComboBox.addItem(name);//在尾部追加
				}
			rs.first();
			if(rs==null) {
				jComboBox.addItem("--"+str+"--");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void upDateComboBox(String strs[],JComboBox jComboBox,String str) {//数据库语句 第二个是要更新的下拉框
		
	
		jComboBox.removeAllItems();
		jComboBox.addItem("--"+str+"--");
		
		for(int i=0;i<strs.length;i++) {
			jComboBox.addItem(strs[i]);
			
		}
				
		
	
		
	}
	
	//向表格当中添加数据
	public static  int addDataTable(ResultSet rs ,DefaultTableModel  model,int index) {
		int count=0;
		model.setNumRows(0);
		String  data[]=new String [index];
		try {
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length;i++) {
					data[i]=rs.getString(i+1);
					
				}
				model.addRow(data);
				
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return count;
		}
	}
	//带序号向表格添加数据
	public static  int addDataTableNum(ResultSet rs ,DefaultTableModel  model,int index) {
		int count=0;
		model.setNumRows(0);
		String  data[]=new String [index+1];
		try {
			if(rs==null) {
				return 0;
			}
			while(rs.next()) {
				count++;
				data[0]= String.valueOf(count);
				for(int i=1;i<data.length;i++) {
					
					data[i]=rs.getString(i);
					
				}
				model.addRow(data);
				
			}
			rs.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return count;
		}
	}
	
	//设置表格大小
	public static void setTableSize(JTable table,int WIDTH,int num) {
		
		int h=0;
		for(int i=1;i<=num;i++) {
			h=h+16;
		}
		table.setPreferredSize(new Dimension(WIDTH-30,h));//设置表格在盘子当中的大下
	}
	
	//给入标签返回位置
	public static int getPosJT(Component[] JLS,Color color) {
		
		for(int i=0;i< JLS.length;i++) {
			if(JLS[i].getForeground().equals(color)){
				return i;
			}
		}
		
		return 1;
		
		
	}
	public static int isMessage(Object[] options,String title,String content) {
		
		int m = JOptionPane.showOptionDialog(null, 
				content, 
				title,
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE,
				null, 
				options, 
				options[0]); 
		return m;
	}
	
}
