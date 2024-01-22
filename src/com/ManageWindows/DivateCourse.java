package com.ManageWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.channels.SelectableChannel;
import java.sql.ResultSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.DynamicMBean;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import com.windows.ManageWindows;

public class DivateCourse extends JPanel{

	
	int WIDTH;
	int HEIGHT;
	public static int onfont=0;
	public  DivateCourse(int x,int y,int width,int height) {
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
		Box boxTwoBox=Box.createHorizontalBox();//定义的是第一行
		boxH.add(boxOneBox);
		
		boxH.add( Box.createVerticalStrut(20));
		boxH.add(boxTwoBox);
		this.add(boxH);
		
		
		initJL("学院",boxOneBox);
		JComboBox cmb=new JComboBox();
		cmb.addItem("--选择学院--");
		boxOneBox.add(cmb);
		Tools.upDateComboBox("select * from college", cmb,"选择学院","college");
		
		
		
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		initJL("授课老师",boxOneBox);
		JComboBox cmb1=new JComboBox();
		cmb1.addItem("--选择老师--");
		boxOneBox.add(cmb1);
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		initJL("课程名称",boxOneBox);
		JComboBox cmb2=new JComboBox();
		cmb2.addItem("--选择课程--");
		boxOneBox.add(cmb2);
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		initJL("上课时间",boxOneBox);
		JComboBox cmb3=new JComboBox();
		cmb3.addItem("--选择时间--");
		boxOneBox.add(cmb3);
		
		JComboBox cmbttBox=new JComboBox();
		
		//this.add(cmbttBox);
		cmb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			JComboBox cmbT = (JComboBox)e.getSource();//调用的局部
				
				if(cmbT.getSelectedIndex()!=0) {
					String string=(String)cmbT.getSelectedItem();
					
			
					string=	"select * from teacher where oncollege="+"'"+string+"'";
					Tools.upDateComboBox(string, cmb1,"选择老师","name");
					
					String data[]=new String [cmb1.getItemCount()-1];
					for(int i=0;i<cmb1.getItemCount()-1;i++) {
						
						
						String string2=	Integer.toString(i+1)+"."+cmb1.getItemAt(i+1);
					
						data[i]=string2;
						
					}
					Tools.upDateComboBox(data, cmb1, "选择老师");
					
					
					Tools.upDateComboBox(string, cmbttBox,"选择老师","id");
					
					
				}
			}
		});
		
		
		
		
		
		cmb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			JComboBox cmbT = (JComboBox)e.getSource();//调用的局部
				
				if(cmbT.getSelectedIndex()!=0) {
					
				
					
					String college=(String)cmb.getSelectedItem();//学院
					String string=(String)cmbT.getSelectedItem();//老师的姓名
					
					String ID=(String)cmbttBox.getItemAt(cmbT.getSelectedIndex());
					
						
				
					string=	"select * from course where colleges='"+college+"' and id='"+ID+"'and star ='1'";
		
					Tools.upDateComboBox(string, cmb2,"选择课程","cname");
				}
			}
		});
		
		
		
		String data[]= {"第一大节","第二大节","第三大节","第四大节","第五大节"};
		Tools.upDateComboBox( data, cmb3, "选择课程");
		

		
		
		//________________________________________________________
		Object columns[] ={"课程编码","学院","授课老师","课程名称","课程时间","班级人数"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,475));//设置整个滚动条窗口的大小
		
		this.add(JS);
		//__________________________________________________________________
		
		
		
		
		
		
		
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		initJL("课程编码", boxTwoBox);
		JTextField jTextField = initJT(8,  boxTwoBox);
		
		JButton jButton=new JButton("撤销选课");
		boxTwoBox.add(jButton);
		
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton1=new JButton("开放选课");
		boxTwoBox.add(jButton1);
		
		
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JButton jButton3=new JButton("查询选课");
		boxTwoBox.add(jButton3);
		boxTwoBox.add(Box.createHorizontalStrut(280));//设置组件之间间隔
		//撤销选课
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jTextField.getText().equals("")) {
					Tools.messageWindows("请输入课程编码");
				}else {
					

					String data1[]= {jTextField.getText()};
					
					int rs1 = Mysqld.upDate("delete from optcous where id=?", data1);
					Tools.setTableSize(table, WIDTH, rs1);
					if(rs1==0) {
						Tools.messageWindows("当前课程不存在");
					}
					if(rs1==-1) {
						Tools.messageWindows("请输入正确的课程编码");
					}
					if(rs1==1) {
						Tools.messageWindows("选课撤销成功");
					}
					
					
				}
				
				
				
			}
		});
	
		
		
		
		//开放选课 
		
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				if(cmb.getSelectedIndex()==0) {
					Tools.messageWindows("请选择学院!");
				}else if(cmb1.getSelectedIndex()==0) {
					Tools.messageWindows("请选择教师!");
				}else if(cmb2.getSelectedIndex()==0) {
					Tools.messageWindows("请选择课程!");
				}else if(cmb3.getSelectedIndex()==0) {
					Tools.messageWindows("请选择时间!");
				}else {
					String A=	(String)cmb.getSelectedItem();//学院
					String B=	(String)cmbttBox.getItemAt(cmb1.getSelectedIndex());
							
							System.out.println( B);
					String C=	(String)cmb2.getSelectedItem();//课程
					String D=	(String)cmb3.getSelectedItem();//时间
					
					String data1[]= {A,B,C};
					
					
					//查询当前内容是有
					String strsqlString="select * from optcous where college=? and teacher=(select name from teacher where id=?) and coursname=?";
					 boolean star = Tools.isHasData(Mysqld.QueryData(strsqlString, data1));
					if(star==true) {
						Tools.messageWindows("当前课程已经添加");
					}else {
						String data[]= {A,B,C,D,B};
						String string="insert into optcous (college,teacher,coursname,clstime,teanum) values(?,(select name from teacher where id=?),?,?,?)";
						Mysqld.upDate(string, data);
						Tools.messageWindows("选课添加成功");
						jButton3.doClick();
						
					}
					
				
				
					
					
				}
				
				
			}
		});	
		//查找数据
		jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			if(jTextField.getText().equals("")) {
				ResultSet rs1 = Mysqld.QueryData("select * from optcous", null);
				int num=Tools.addDataTable( rs1 , model, 6);
				Tools.setTableSize(table, WIDTH, num);
				if (num==0) {
					Tools.messageWindows("当前没有课程数据");
				}
			
			}else {
				//查询一个
		
				String st=jTextField.getText();
				String data[]= {st};
				ResultSet rs1 = Mysqld.QueryData("select * from optcous where id=?", data);
				
				int num=Tools.addDataTable( rs1 , model, 6);
				Tools.setTableSize(table, WIDTH, num);
				if (num==0) {
					Tools.messageWindows("当前课程编码不存在！");
				}
				if (num==-1) {
					Tools.messageWindows("请输入正确格式的课程编码");
				}
			
			}
			}
		});

		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//____________________________________________________________________
	       new Timer().schedule(new TimerTask() {
	    	   int a=0;
	            @Override
	            public void run() {
	                try {
	                    //根据不同
	   
	                if(a==0) {
	                	  if(ManageWindows.jLabel7!=null&& ManageWindows.jLabel7.getForeground().equals(new Color(30,144,255))) {
		                		Tools.upDateComboBox("select * from college", cmb,"请选择学院","college");
		                		a=1;
		                }
	                }else {
	                	if(ManageWindows.jLabel7!=null&& ManageWindows.jLabel7.getForeground().equals(new Color(30,144,255))) {
	                		
	                		
	                }else {
	                	a=0;
	                }
	                }
	                	
	              
	               
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }, 0, 1000);
	
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
