package com.ManageWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.windows.ManageWindows;

public class GetSoureMan extends JPanel{
	int WIDTH;
	int HEIGHT;
	
	public GetSoureMan(int x,int y,int width,int height) {
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
		
		cmb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			JComboBox cmbT = (JComboBox)e.getSource();//调用的局部
				
				if(cmbT.getSelectedIndex()!=0) {
					String string=(String)cmbT.getSelectedItem();
				
					string=	"select * from teacher where oncollege="+"'"+string+"'";
					Tools.upDateComboBox(string, cmbttBox,"选择老师","id");
					Tools.upDateComboBox(string, cmb1,"选择老师","name");
					
					//? and teachname=? and cname=? and star ='1'
					String data[]=new String [cmb1.getItemCount()-1];
					for(int i=0;i<cmb1.getItemCount()-1;i++) {
						
						
						String string2=	Integer.toString(i+1)+"."+cmb1.getItemAt(i+1);
					
						data[i]=string2;
						
					}
					Tools.upDateComboBox(data, cmb1, "选择老师");
					
					
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
		JButton jButton=new JButton("查询课程");
		this.add(jButton);
		
		Object columns[] ={"课程编码","学院","老师工号","授课老师","课程名称","课程时间","班级人数"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,475));//设置整个滚动条窗口的大小
		
		this.add(JS);
		//__________________________________________________________________
		
		
		String data[]= {"第一大节","第二大节","第三大节","第四大节","第五大节"};
		Tools.upDateComboBox( data, cmb3, "选择课程");
		
		
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cmb.getSelectedIndex()==0) {
					//查询全部
					
					
					
					Tools.setTableSize(table, WIDTH, Tools.addDataTable(Mysqld.QueryData("select * from optcous", null),model,7));
				}else {
					//按条件查询
			
					if(cmb1.getSelectedIndex()==0) {
						String data[]= {(String)cmb.getSelectedItem()};
						Tools.setTableSize(table, WIDTH, Tools.addDataTable(Mysqld.QueryData("select * from optcous where college=?", data),model,7));
					}else {
						
						
						if(cmb2.getSelectedIndex()==0) {
							String data[]= {(String)cmb.getSelectedItem(),(String)cmbttBox.getItemAt(cmb1.getSelectedIndex())};
					
							Tools.setTableSize(table, WIDTH, Tools.addDataTable(Mysqld.QueryData("select * from optcous where college=? and id=?", data),model,7));
						}else {
							
							
							if(cmb3.getSelectedIndex()==0) {
								String data[]= {(String)cmb.getSelectedItem(),(String)cmbttBox.getItemAt(cmb1.getSelectedIndex()),(String)cmb2.getSelectedItem()};
								Tools.setTableSize(table, WIDTH, Tools.addDataTable(Mysqld.QueryData("select * from optcous where college=? and id=? and coursname=?", data),model,7));
								
							}else {
								
								
								
								String data[]= {(String)cmb.getSelectedItem(),(String)cmbttBox.getItemAt(cmb1.getSelectedIndex()),(String)cmb2.getSelectedItem(),(String)cmb3.getSelectedItem()};
								Tools.setTableSize(table, WIDTH, Tools.addDataTable(Mysqld.QueryData("select * from optcous where college=? and id=? and coursname=? and clstime=?", data),model,7));
							}
							
						}
						
					}
					
					
					
					
					
					
				}
				
			}
		});
		

		//________________________________________________________

		
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

	//合并字符串
	String []conNect(String data[],String str){
		
		String data1[]=new String[ data.length+1];
		int i=0;
		for(;i<data.length;i++) {
			data1[i]=data[i];
		}
		data1[i]=str;
		return data1;
		
	}
	
	
	//通过传入rs将放入数组
	String [] getSqlData(ResultSet rs,int count) {
		String temp[] = null;
		int index=0;
		try {
		
			
				String data[]=new String [count];
				while(rs.next()) {
					
					for(int i=0;i<count;i++) {
						data[i]=rs.getString(i+1);
					}
					
				}
				rs.close();
				return data;
				
			
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
		
		
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
