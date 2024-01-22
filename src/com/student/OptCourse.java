package com.student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.style.StyleFont;
import com.tools.*;
import com.until.DBUtil;
import com.windows.ManageWindows;

public class OptCourse{
	int WIDTH;
	int HEIGHT;
	JTable table;
	String stunmu;
	int star=0;
	JPanel jPanel=new JPanel();
	DefaultTableModel model;
	int star_1=0;//0 1 
	public OptCourse(int x,int y,int width,int height,String stunum) {
		//设置坐标和  宽高
		this.stunmu=stunum;
		this.WIDTH=width;
		this.HEIGHT=height;
		this.stunmu=stunum;
		jPanel.setBounds(x, y, width, height);
		init();
	}
	
	void init() {
		
		jPanel.setBackground(new Color(255,255,255));
		jPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,15));
		
		JButton jButton1 =new JButton("刷新当前页面");
		
		JButton jButton=new JButton();
		//____________________________________________________________________
		Object columns[] ={"课程编码","学院","教师姓名","课程名称","时间","当前人数","选课/退课"};
		Table t1Table=new Table(columns);
		
		table = t1Table.getTables();
		this.table=table;
		JScrollPane JS = t1Table.getJScrollPane();
		model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,240));//设置整个滚动条窗口的大小
		jPanel.add(JS);
		
		ResultSet rs = Mysqld.QueryData("select * from optcous", null);
		int num=addDataTable(rs, model, 6);
		
		
		
		Tools.setTableSize(table, WIDTH, num);
		
		table.setSelectionBackground(table.getBackground());
		table.setSelectionForeground(Color.MAGENTA);
		
		
		jButton1.addActionListener(new ActionListener() {
			
		
		

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ResultSet rs = Mysqld.QueryData("select * from optcous", null);
				
				jPanel.remove(0);
				
				
				
				Object columns[] ={"课程编码","学院","教师姓名","课程名称","时间","当前人数","选课/退课"};
				Table t1Table=new Table(columns);
				
				table = t1Table.getTables();
				JScrollPane JS = t1Table.getJScrollPane();
				model = t1Table.getModel();
				JS.setPreferredSize(new Dimension(WIDTH-40,240));//设置整个滚动条窗口的大小
				jPanel.add(JS,0);
				SwingUtilities.updateComponentTreeUI(jPanel);//添加或删除组件后,更新窗口
				
				ResultSet rs1 = Mysqld.QueryData("select id,college,teacher,coursname,clstime,numpeo from optcous", null);
				int num=addDataTable(rs1, model, 6);
				Tools.setTableSize(table, WIDTH, num);
				
				table.setSelectionBackground(table.getBackground());
				table.setSelectionForeground(Color.MAGENTA);
			
				
				for (int i = 0; i < columns.length; i++) {
					//遍历每一行
					TableColumn tableColumn = table.getColumn(columns[i]);//获取每一列
				
					tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
						 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
							 
							 //0~7行  6  7
							 if(table.getValueAt( row, column).equals("退选")) {
								 setForeground(Color.red);
							 }
							 if(table.getValueAt( row, column).equals("选课")) {
								 setForeground(Color.BLUE);
							 }
							
					
							 setHorizontalAlignment(SwingConstants.CENTER);
							 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
						 }
					
					});
					
				}
				
				
				
				table.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
						String valString=	(String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());//虎丘行值
						String courString=(String) table.getValueAt(table.getSelectedRow(), 3);//获取选课名称】
						String data11[]=setDa(courString,stunmu);
						Object data[]= {"确定","取消"};
						String data_ne[]=new String[data11.length+1];
						int j_s=0;
						for(int i=0;i<data11.length;i++) {
							data_ne[i]=data11[i];
							j_s=i;
						}
						data_ne[j_s+1]=(String) table.getValueAt(table.getSelectedRow(), 0);//获取选课名称】
						
						
					int x=	table.getMousePosition().x;
					int y=	table.getMousePosition().y;
					
				
						if(valString.equals("选课")) {
							int num=Tools.isMessage(data, "选课信息", "是否确认选课");
							
							
							 if(star==2) {
								 Tools.messageWindows("选课上限最多两门!");
								 
							 }else
							
							
							
							if(num==0) {
								// 学号  姓名  学院 专业  入学年份
								star++;//当前课程数量
								
								
								int	a=	Mysqld.upDate("insert into studengcours  (college,pro,optcouse,stunum,cousid) VALUES (?,?,?,?,?)", data_ne);
								//更改课程人数
								;
								String data_s[]= {(String)table.getValueAt(table.getSelectedRow(), 1),
										(String)table.getValueAt(table.getSelectedRow(), 2),
										(String)table.getValueAt(table.getSelectedRow(), 3),
										(String)table.getValueAt(table.getSelectedRow(), 4)};
								Mysqld.upDate("update optcous set numpeo=numpeo+1 where college=? and teacher=? and coursname=?  and clstime=?", data_s);
								
								TableColumn tableColumn = table.getColumn(columns[6]);//获取每一列
								tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
									 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
										 
										 String courString=(String) table.getValueAt(table.getSelectedRow(), 3);
										 int	num= readHasOptCous(setDa(courString,stunmu));
										
										 if(column==6&&num==1&&row==table.getSelectedRow()) {
											
											 table.setValueAt("退选", row, column);
											 
										
										 }
										 
										 
										 if(table.getValueAt( row, column).equals("退选")) {
											 setForeground(Color.red);
										 }
										 if(table.getValueAt( row, column).equals("选课")) {
											 setForeground(Color.BLUE);
										 }
										 
										 setHorizontalAlignment(SwingConstants.CENTER);
										 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
									 }
								
								});
								
								jButton1.doClick();
								jButton.doClick();
							}
						}
						
						
						
						if(valString.equals("退选")) {
							int num=Tools.isMessage(data, "选课信息", "是否确认退课");
							if(num==0) {
								star--;
						
								//从数据库当中删除
								int	num1= Mysqld.upDate("delete from studengcours where college=? and pro=? and optcouse=? and stunum=?",data11);
								String data_s[]= {(String)table.getValueAt(table.getSelectedRow(), 1),
										(String)table.getValueAt(table.getSelectedRow(), 2),
										(String)table.getValueAt(table.getSelectedRow(), 3),
										(String)table.getValueAt(table.getSelectedRow(), 4)};
								Mysqld.upDate("update optcous set numpeo=numpeo-1 where college=? and teacher=? and coursname=?  and clstime=?", data_s);
						
								TableColumn tableColumn = table.getColumn(columns[6]);//获取每一列
								
								tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
									 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
										 
										 String courString=(String) table.getValueAt(table.getSelectedRow(), 3);
										 int	num= readHasOptCous(setDa(courString,stunmu));
										 if(column==6&&num==0&&row==table.getSelectedRow()) {
											
											 
											 table.setValueAt("选课", row, column);
											 
										 }
										 
										 if(table.getValueAt( row, column).equals("退选")) {
											 setForeground(Color.red);
										 }
										 if(table.getValueAt( row, column).equals("选课")) {
											 setForeground(Color.BLUE);
										 }
										 
										 
										 setHorizontalAlignment(SwingConstants.CENTER);
										 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
									 }
								
								});
								
								jButton1.doClick();
								jButton.doClick();
							}
						}
						
						
						
						
						
						
						
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
					
				
				}
			
						
						);
					
				//表格数据改变时候刷新
				
				
				
				
				
				
				
		
				
			}
				
		}
			);
		
		
		//_______________________________________________________
		//表格渲染器 为每个表格加这个渲染器
		for (int i = 0; i < columns.length; i++) {
			//遍历每一行
			TableColumn tableColumn = table.getColumn(columns[i]);//获取每一列
		
			tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
				 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
					 
					 //0~7行  6  7
					 if(table.getValueAt( row, column).equals("退选")) {
						 setForeground(Color.red);
						 
					
					 }
					 if(table.getValueAt( row, column).equals("选课")) {
						 setForeground(Color.BLUE);
						
					 }
			
			
					 setHorizontalAlignment(SwingConstants.CENTER);
					 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
				 }
			
			});
			
		}
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				String valString=	(String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());//虎丘行值
				String courString=(String) table.getValueAt(table.getSelectedRow(), 3);//获取选课名称】
				String data11[]=setDa(courString,stunmu);
				Object data[]= {"确定","取消"};
				String data_ne[]=new String[data11.length+1];
				int j_s=0;
				for(int i=0;i<data11.length;i++) {
					data_ne[i]=data11[i];
					j_s=i;
				}
				data_ne[j_s+1]=(String) table.getValueAt(table.getSelectedRow(), 0);//获取选课名称】
			
				
			int x=	table.getMousePosition().x;
			int y=	table.getMousePosition().y;
			
		
				if(valString.equals("选课")) {
					int num=Tools.isMessage(data, "选课信息", "是否确认选课");
					
					
					 if(star==2) {
						 Tools.messageWindows("选课上限最多两门!");
						 
					 }else
					
					
					
					if(num==0) {
						// 学号  姓名  学院 专业  入学年份
						star++;//当前课程数量
						
						
						int	a=	Mysqld.upDate("insert into studengcours  (college,pro,optcouse,stunum,cousid) VALUES (?,?,?,?,?)", data_ne);
						//更改课程人数
						;
						String data_s[]= {(String)table.getValueAt(table.getSelectedRow(), 1),
								(String)table.getValueAt(table.getSelectedRow(), 2),
								(String)table.getValueAt(table.getSelectedRow(), 3),
								(String)table.getValueAt(table.getSelectedRow(), 4)};
						Mysqld.upDate("update optcous set numpeo=numpeo+1 where college=? and teacher=? and coursname=?  and clstime=?", data_s);
						
						TableColumn tableColumn = table.getColumn(columns[6]);//获取每一列
						tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
							 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
								 
								 String courString=(String) table.getValueAt(table.getSelectedRow(), 3);
								 int	num= readHasOptCous(setDa(courString,stunmu));
								
								 if(column==6&&num==1&&row==table.getSelectedRow()) {
									
									 table.setValueAt("退选", row, column);
									 
								
								 }
								 
								 
								 
								 if(table.getValueAt( row, column).equals("退选")) {
									 setForeground(Color.red);
									 
									
								 }
								 if(table.getValueAt( row, column).equals("选课")) {
									 setForeground(Color.BLUE);
									
									
								 }
								 
								 setHorizontalAlignment(SwingConstants.CENTER);
								 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
							 }
						
						});
						
						jButton1.doClick();
						jButton.doClick();
					}
				}
				
				
				
				if(valString.equals("退选")) {
					int num=Tools.isMessage(data, "选课信息", "是否确认退课");
					if(num==0) {
						star--;
				
						//从数据库当中删除
						int	num1= Mysqld.upDate("delete from studengcours where college=? and pro=? and optcouse=? and stunum=?",data11);
						String data_s[]= {(String)table.getValueAt(table.getSelectedRow(), 1),
								(String)table.getValueAt(table.getSelectedRow(), 2),
								(String)table.getValueAt(table.getSelectedRow(), 3),
								(String)table.getValueAt(table.getSelectedRow(), 4)};
						Mysqld.upDate("update optcous set numpeo=numpeo-1 where college=? and teacher=? and coursname=?  and clstime=?", data_s);
				
						TableColumn tableColumn = table.getColumn(columns[6]);//获取每一列
						
						tableColumn.setCellRenderer(new  DefaultTableCellRenderer() {
							 public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
								 
								 String courString=(String) table.getValueAt(table.getSelectedRow(), 3);
								 int	num= readHasOptCous(setDa(courString,stunmu));
								 if(column==6&&num==0&&row==table.getSelectedRow()) {
									
									 
									 table.setValueAt("选课", row, column);
									 
								 }
								 
								 if(table.getValueAt( row, column).equals("退选")) {
									 setForeground(Color.red);
									 table.setSelectionForeground(Color.red);
								 }
								 if(table.getValueAt( row, column).equals("选课")) {
									 setForeground(Color.BLUE);
									 table.setSelectionForeground(Color.BLUE);
								 }
								 
								
								 setHorizontalAlignment(SwingConstants.CENTER);
								 return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
							 }
						
						});
						
						jButton1.doClick();
						jButton.doClick();
					}
				}
				
				
				
				
				
				
				
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
			
		JLabel jLabel=new JLabel("◎ 选修课程：已选课程");
		jPanel.add(jLabel);
		jPanel.add(jButton1);
		jLabel.setForeground(new Color(102,204,255));
		jLabel.setFont(new Font("微软雅黑",Font.BOLD, 15));
		
		//表格数据改变时候刷新
		//________________________________________________________
		Object A[] ={"课程名称","授课教师","上课时间","分数","状态"};
		Table B=new Table(A);
		JTable C = B.getTables();
		JScrollPane D = B.getJScrollPane();
		D.setPreferredSize(new Dimension(WIDTH-40,290));//设置整个滚动条窗口的大小
		DefaultTableModel model1 = B.getModel();
		jPanel.add(D);
		//__________________________________________________________________
		
		
		//jPanel.add(jButton);
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				ResultSet	rs=	Mysqld.QueryData("select cousid from studengcours where stunum='"+stunmu+"'",null);
				int count=0;
				try {
					model1.setRowCount(0);
					
					while(rs.next()) {
						String string=rs.getString("cousid");
						String data[]= {string,string,stunmu};
				
						String aString="select optcouse,"
								+ "			(select teacher from optcous where id=?)  ,"
								+ "			(select clstime from optcous where id=?)  ,"
								+ "			if(ISNULL(count)=1,'0', count) ,"
								+ "			if(ISNULL(count)=1,'未批阅','成绩已下发')"
								+ "			from studengcours where stunum=?";
						ResultSet rs1 = Mysqld.QueryData(aString,data);
						
						String  data1[]=new String [5];
						while(rs1.next()) {
							count++;
							for(int i=0;i<data1.length;i++) {
								data1[i]=rs1.getString(i+1);
								
							}
						
							model1.addRow(data1);
							
						}
						rs1.close();
					}
					model1.setRowCount(2);
					
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//
				if(count==4) {
					Tools.setTableSize(C, WIDTH,2 );
				}else {
					Tools.setTableSize(C, WIDTH,1 );
				}
				
				
				
				
		

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				//_____________________________________________
			
				
			}
		});
		jButton.doClick();
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public JPanel getJP() {
		return jPanel;
	}
	
	
	//初始化标签
	public   int addDataTable(ResultSet rs ,DefaultTableModel  model,int index) {
		int count=0;
		model.setNumRows(0);
star=0;
		String  data[]=new String [index+1];
		try {
			
			while(rs.next()) {
				count++;
				for(int i=0;i<data.length-1;i++) {
					data[i]=rs.getString(i+1);
				}
			
				//如何这个东西有值 则为退选 没有则未选课
				String courString=data[3];//获取选课名称】
				
				int	num= readHasOptCous(setDa(courString,stunmu));
				if(num==0) {
					data[index]="选课";
				}else {
					data[index]="退选";
					star++;
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
//读取数据库
int readHasOptCous(String data[]) {
	
	String sqlStr="select * from studengcours where college=? and pro=? and optcouse=? and stunum=?";
	PreparedStatement preSql;//select * from ? (数据库名字)
	ResultSet rs;
	int num=0;
	try {
		
		preSql=DBUtil.con.prepareStatement(sqlStr);
		
			for(int i=0;i<data.length;i++) {
				//遍历传入的data数据 把他存入 数据库语句   
				preSql.setString(i+1,data[i]);
			}

			rs=preSql.executeQuery();
			while(rs.next()) {
				num++;
			}
			rs.close();
			
		return num;//如果数字 就>0就是执行成功   -1 就是失败  
	
		
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
		return -1;
	}
	
	
}
String [] setDa(String a,String b) {
	
	String data[]=new String[4];
	ResultSet rs = Mysqld.QueryData("select oncollege,peojob,name from student where id='"+stunmu+"'", null);
	String A="";
	String B="";

	try {
	
		while(rs.next()) {
			data[0]=rs.getString(1);//学院
			data[1]=	rs.getString(2);//专业
	
		}
		data[2]=a;
		data[3]=b;
		rs.close();
		return data;

	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		return data;
	}
	
}


}
