package com.ManageWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;

import com.style.StyleFont;
import com.tools.Mysqld;
import com.tools.Table;
import com.tools.Tools;

public class Course extends JPanel{
	int WIDTH;
	int HEIGHT;
	public static int onfont=0;
	public Course(int x,int y,int width,int height) {
		//设置坐标和  宽高
		
		this.WIDTH=width;
		this.HEIGHT=height;
		this.setBounds(x, y, width, height);
		init();
		

		
	}
	
	void init() {
		
		this.setBackground(new Color(255,255,255));
		this.setLayout(new FlowLayout(FlowLayout.LEFT,20,15));
		Box boxH=Box.createVerticalBox();
		Box boxOneBox=Box.createHorizontalBox();//定义的是第一行
		boxH.add(boxOneBox);
		this.add(boxH);
		
		initJL("申请状态",boxOneBox);
		
		JCheckBox checkbox1=new JCheckBox("申请");
		JCheckBox checkbox2=new JCheckBox("拒绝");
		JCheckBox checkbox3=new JCheckBox("同意");
	
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(checkbox1);
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(checkbox2);
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(checkbox3);

		
		checkbox1.setOpaque(false);
		checkbox2.setOpaque(false);
		checkbox3.setOpaque(false);
	
		//点击任意一行 如果他是申请 则跳出弹窗 是否同意申请
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton=new JButton("查看申请状态");
		boxOneBox.add(jButton);
		
		
		
		//________________________________________________________
		Object columns[] ={"序号","授课老师","专业名称","申请状态"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,540));//设置整个滚动条窗口的大小
		
		
		this.add(JS);
		//__________________________________________________________________
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			boolean A = checkbox1.isSelected();
			boolean B=	checkbox2.isSelected();
			boolean C=	checkbox3.isSelected();
			
			if(A==false && B==false && C==false) {
				checkbox1.setSelected(true);
				A=true;
			}
			
			//0 代表申请中的状态 1代表同意状态 2 代表拒绝的状态  3代表撤销的状态
			String sqlA="";
			String sqlB="";
			String sqlC="";
			
			
			int learn=0;
			if(A==true) {
				if(learn==0) {
					sqlA="  star='0'  ";
				}else {
					sqlA="  or star='0'  ";
				}
				
				learn++;
				
			}
			
			if(B==true) {
				if(learn==0) {
					sqlB="  star='2'  ";
				}else {
					sqlB="  or star='2'  ";
				}
				learn++;
			}
			
			if(C==true) {
				if(learn==0) {
					sqlC="  star='1'  ";
				}else {
					sqlC="  or star='1'  ";
				}
				learn++;
			}

			
			String strsqlString="select teachname, cname, if(star=0,'正在申请',if(star=1,'同意申请',if(star=2,'拒绝申请','撤销申请'))) from course  where "+sqlA+sqlB+sqlC;
		
		
	
			
			ResultSet rs = Mysqld.QueryData(strsqlString, null);
			
			int num=Tools.addDataTableNum(rs, model,3);
			Tools.setTableSize(table, WIDTH, num);
			if(num==0) {
				Tools.messageWindows("未查找到任何内容");
			}
			
			Tools.setTableSize(table, WIDTH, num);
			
			}
		});
		
		//表格监听事件
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable tab = (JTable)e.getSource();
				
				int x=tab.getMousePosition().x;
				int y=tab.getMousePosition().y;
				int cellPosx=x/(tab.getWidth()/tab.getColumnCount());
				int cellPosy=y/(tab.getHeight()/tab.getRowCount());
				int row=tab.getSelectedRow();
				int colunme=tab.getSelectedColumn();
				
				
			
				
				//同意0  拒绝1 
				if(tab.getValueAt(row,3).equals("正在申请")) {
					Object[] options ={ "同意申请", "拒绝申请" };  //自定义按钮上的文字
					int m=Tools.isMessage(options, "申请消息", "是否同意课程申请？");
					if (m==0) {
						//将数据值改成 1
						//同意申请
						String data[]= {(String) tab.getValueAt(row,1),(String) tab.getValueAt(row,2)};
						Mysqld.upDate("update course set star='1' where teachname=? and cname=?", data);
						checkbox1.setSelected(false);
						checkbox2.setSelected(false);
						checkbox3.setSelected(true);
						jButton.doClick();
						
						
					}else if(m==1) {
						String data[]= {(String) tab.getValueAt(row,1),(String) tab.getValueAt(row,2)};
						Mysqld.upDate("update course set star='2' where teachname=? and cname=?", data);
						checkbox1.setSelected(false);
						checkbox2.setSelected(true);
						checkbox3.setSelected(false);
						jButton.doClick();
					}
				}
				//
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
	
		
		
		
		
		

		
		
	}
	//初始化标签
	void initJL(String text,Box boxH) {
		JLabel jLabel=new JLabel(text);
		jLabel.setFont(StyleFont.Item);
		boxH.add(jLabel);
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
	}
	//初始化文本框
	JTextField initJT(int num,Box boxH) {
		JTextField jTextField =new JTextField(num);
		boxH.add(jTextField);
		boxH.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		return jTextField ;
	}
}
