package com.teacher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;

import com.style.StyleFont;
import com.tools.DataOther;
import com.tools.Mysqld;
import com.tools.Table;
import com.tools.Tools;

public class CouseMan extends JPanel{
	int WIDTH;
	int HEIGHT;
	String TeaName;
	public CouseMan(int x,int y,int width,int height,String TeaName) {
		//设置坐标和  宽高
		this.TeaName=TeaName;
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
		
		initJL("课程名字",boxOneBox);
		JTextField jTextField=initJT(8, boxOneBox);
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton=new JButton("申请课程");
		boxOneBox.add(jButton);
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton1=new JButton("撤销课程申请");
		boxOneBox.add(jButton1);
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton2=new JButton("查看课程申请状态");
		boxOneBox.add(jButton2);
		
		
		//____________________________________________________________________
		Object columns[] ={"序号","课程名称","申请状态"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,540));//设置整个滚动条窗口的大小
		this.add(JS);
		
		//__________________________________________________________________________________
		//0 代表申请中的状态 1代表同意状态 2 代表拒绝的状态  3代表撤销的状态
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=jTextField.getText();
				if(str.equals("")) {
					Tools.messageWindows("请输入课程名字");
					
				}else {
					
					
					String data1[]= {TeaName,TeaName,TeaName,str};
					
					
					
					ResultSet rs = Mysqld.QueryData("select * from course where colleges=(select oncollege from teacher where id=?) and  teachname=(select name from teacher where id=?) and cname=? and id=?", data1);
					boolean sta = Tools.isHasData(rs);
				
					if(sta) {
						Tools.messageWindows("切勿重复申请");
					}else {
						String data11[]= {TeaName,TeaName,TeaName,str};
						int num=Mysqld.upDate("insert into course (id,colleges,teachname,cname) values(?,(select oncollege from teacher where id=?),(select name from teacher where id=?),?)", data11);
						if(num!=-1) {
							Tools.messageWindows("提交申请完成,等待管理员审核");
							jButton2.doClick();
						}
						
					}
				}
			}
		});
		//推选
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=jTextField.getText();
				if(str.equals("")) {
					Tools.messageWindows("请输入课程名字");
					
				}else {
					
					String data1[]= {TeaName,str};
					ResultSet rs = Mysqld.QueryData("select * from course where teachname=(select name from teacher where id=?) and cname=?", data1);
					boolean sta = Tools.isHasData(rs);
					if(sta) {
						
						String sta1 =DataOther.showCoursSta("select * from course where  teachname=(select name from teacher where id=?) and cname=?", data1);
						
						if(sta1.equals("0")) {
							String data11[]= {"3",TeaName,str};
							DataOther.setCoursSta("update course set star=? where  teachname=(select name from teacher where id=?) and cname=?",data11);
							
							String data[]= {TeaName,str};
							String strsqlString="select cname, if(star=0,\"正在申请\",if(star=1,\"申请成功\",if(star=2,\"拒绝申请\",\"撤销申请\"))) from course where teachname=(select name from teacher where id=?) and cname=?";
							ResultSet rs1 = Mysqld.QueryData(strsqlString, data);
							int	num =Tools.addDataTableNum(rs1, model, 2);
							Tools.setTableSize(table, WIDTH, num);
							
						}else if(sta1.equals("")){
							
							Tools.messageWindows("申请列表没有此课程");
							
						}else {
							
							Tools.messageWindows("当前课程状态不能退选");
						}
						
					}else {
					
						Tools.messageWindows("请输入正确课程名称");
					
						
					}
				}
			}
		});
		
	//查询功能
		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=jTextField.getText();
				if(str.equals("")) {
					//查询全部
				String data[]= {TeaName};
				String strsqlString="select cname, if(star=0,\"正在申请\",if(star=1,\"申请成功\",if(star=2,\"拒绝申请\",\"撤销申请\"))) from course where teachname=(select name from teacher where id=?)";
				ResultSet rs = Mysqld.QueryData(strsqlString, data);
				int	num =Tools.addDataTableNum(rs, model, 2);
				Tools.setTableSize(table, WIDTH, num);
					
				}else {
					String data[]= {TeaName,str};
					String strsqlString="select cname, if(star=0,\"正在申请\",if(star=1,\"申请成功\",if(star=2,\"拒绝申请\",\"撤销申请\"))) from course where teachname=(select name from teacher where id=?) and cname=?";
					ResultSet rs = Mysqld.QueryData(strsqlString,data);
					int	num =Tools.addDataTableNum(rs, model, 2);
					Tools.setTableSize(table, WIDTH, num);
					
				}
			}
			
		});
		//刷新
		//____________________________________________________________________
	       new Timer().schedule(new TimerTask() {
	            @Override
	            public void run() {
	                try {
	                    //根据不同
	                
	                if(table.getRowCount()>1) {
	                	//查询全部
	                	String data[]= {TeaName};
	    				String strsqlString="select cname, if(star=0,\"正在申请\",if(star=1,\"申请成功\",if(star=2,\"拒绝申请\",\"撤销申请\"))) from course where teachname=(select name from teacher where id=?)";
	    				ResultSet rs = Mysqld.QueryData(strsqlString, data);
	    				int	num =Tools.addDataTableNum(rs, model, 2);
	    				Tools.setTableSize(table, WIDTH, num);
	                }
	                
	              
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }, 0, 10000);
	
	//___________________________________________________________________________

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
