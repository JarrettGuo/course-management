package com.ManageWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.DynamicMBean;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

public class TeacherMan extends JPanel{
	
	int WIDTH;
	int HEIGHT;
	
	public TeacherMan(int x,int y,int width,int height) {
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
		
		initJL("姓名",boxOneBox);
		JTextField jTextField=initJT(8, boxOneBox);
		
		
		
		initJL("性别",boxOneBox);
		
		 ButtonGroup g = new ButtonGroup();//建立一个组
		 JRadioButton box1 = new JRadioButton("男",true);
		 JRadioButton box2 = new JRadioButton("女",false);
		 box1.setFont(StyleFont.Item);
		 box2.setFont(StyleFont.Item);
		 box1.setOpaque(false);
		 box2.setOpaque(false);
		 boxOneBox.add(box1);
		 boxOneBox.add(box2);
		 g.add(box1);
		 g.add(box2);
		 
		 boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		 initJL("学院名称",boxOneBox);
		 JComboBox cmb=new JComboBox(); 
		 boxOneBox.add(cmb);
		 Tools.upDateComboBox("select * from college", cmb,"请选择学院","college");
		 boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		
		initJL("学历",boxOneBox);
		JComboBox cmb1=new JComboBox(); 
		boxOneBox.add(cmb1);
		String str[]= {"博士生","研究生","本科","专科"};
		Tools.upDateComboBox(str, cmb1,"请选择学历");
		
		//5按钮  编号
		Box boxTwoBox=Box.createHorizontalBox();
		boxH.add(Box.createVerticalStrut(20));
		boxH.add(boxTwoBox);
		
		
		initJL("教师工号",boxTwoBox);
		JTextField jTextField2=initJT(8, boxTwoBox);
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JButton jButton=new JButton("查找教师");
		boxTwoBox.add(jButton);
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JButton jButton1=new JButton("增加教师");
		boxTwoBox.add(jButton1);
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JButton jButton2=new JButton("删除教师");
		boxTwoBox.add(jButton2);
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JButton jButton3=new JButton("更改教师");
		boxTwoBox.add(jButton3);
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		
		
	
		//____________________________________________________________________
		Object columns[] ={"序号","老师工号","姓名","所在学院","学历","性别","入职年份"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,475));//设置整个滚动条窗口的大小
		this.add(JS);
		
	
		//_______________________________________________________________________
		//添加教师
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name=jTextField.getText();
				String sexString;
				if(box1.isSelected()) {
					sexString="男";
				}else {
					sexString="女";
				}
				if(name.equals("")) {
					Tools.messageWindows("请输入姓名");
				}else if(cmb.getSelectedIndex()==0){
					Tools.messageWindows("请选择学院");
				}else if(cmb1.getSelectedIndex()==0){
					Tools.messageWindows("请选择学历");
				}else {
					
					String collegeString=(String)cmb.getSelectedItem();
					String edu=(String)cmb1.getSelectedItem();
					String string="insert into teacher (id,name,oncollege,edu,sex,inyear) values  (?,?,?,?,?,CONCAT(year(now()),'年'))";
					
					String  num = null;//工号是主健
					int i=1;
					while(true) {
						Calendar cal = Calendar.getInstance();
				        int year = cal.get(Calendar.YEAR);
				        String s = String.valueOf(year);
				        //21   学院编号
				        
				        String data1[]= {collegeString};
				        ResultSet rs1 = Mysqld.QueryData("select * from  college where college=?", data1);
				        String coid="0";
						try {
							while(rs1.next()) {
								coid=rs1.getString("id");//是学员编号
							}
							
							rs1.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String ss = String.valueOf(i);//是序号
						//202137 1
						//  0
						 num=s+coid+ss;
						 
						String data2[]= {"1"+s+coid+"%"};
					//	ln(s+coid);
						String strsqlString=" select * from teacher where id like ? ORDER BY id asc";
						ResultSet rs2 = Mysqld.QueryData(strsqlString, data2);
						try {
					
							while(rs2.next()) {
								
								i++;
						
							}
						
							num= String.valueOf(i);//是序号  //把当前学号这个学院的教师 全部取出来
							num=s+coid+num;
					
							

							rs2.close();
							
						///num=String.valueOf(Integer.parseInt(num)+1);
						//ln(num);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						num="1"+num;
						String data[]= {num,name,collegeString,edu,sexString};
						int rs = Mysqld.upDate(string, data);
						
						String data4[]= {num,num,"2"};
						Mysqld.upDate("insert into users (account,password,pow) values(?,?,?)", data4);
						
						if(rs!=-1) {
							String data11[]= {num};
							ResultSet rs_1=Mysqld.QueryData("select * from teacher where id=?",data11);
							int num_s=Tools.addDataTableNum(rs_1, model, 6);
							Tools.setTableSize(	table, WIDTH,num_s );
										
							//Tools.messageWindows("添加成功");
							break;
						}
				
						
					}
					
					
			
					
				}
				
				
			}
		});
	
		//查询功能
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			String numString=	jTextField2.getText();
			
			if(numString.equals("")) {
				
				ResultSet rs = Mysqld.QueryData("select * from teacher", null);
				
				
				
				int num=Tools.addDataTableNum(rs, model, 6);
				Tools.setTableSize(table, WIDTH, num);
				if(num==0) {
					Tools.messageWindows("数据结果为空");
				}
				
				
				
			}else {
				String data[]= {jTextField2.getText()};
				ResultSet rs = Mysqld.QueryData("select * from teacher where id=?", data);
				
				int num=Tools.addDataTableNum(rs, model, 6);
				Tools.setTableSize(table, WIDTH, num);
				if(num==0) {
					Tools.messageWindows("数据结果为空");
				}
				
				
				
				rs = Mysqld.QueryData("select * from teacher where id=?", data);
				
				try {
					while(rs.next()) {
						
						jTextField.setText(rs.getString("name"));
						if(rs.getString("sex").equals("男")) {
							box1.setSelected(true);
							
						}else {
							box2.setSelected(true);
						}
						
						
						for(int i=0;i<cmb.getItemCount();i++) {
							if(rs.getString("oncollege").equals(cmb.getItemAt(i))) {
								cmb.setSelectedIndex(i);
							}
						}
						
						for(int i=0;i<cmb1.getItemCount();i++) {
							if(rs.getString("edu").equals(cmb1.getItemAt(i))) {
								cmb1.setSelectedIndex(i);
							}
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			}
		});
		
		//删除功能
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String numString=	jTextField2.getText();
				if(numString.equals("")) {
					Tools.messageWindows("请输入教师工号");
				}else {
					
					String data[]= {numString};
					
					int sta=Mysqld.upDate("DELETE from teacher where id =?", data);
					if(sta==0) {
						Tools.messageWindows("删除教师失败,请检查工号");
					}
					if(sta==-1) {
						Tools.messageWindows("请检查输入格式");
					}
					if(sta>=1) {
						
						ResultSet rs = Mysqld.QueryData("select * from teacher", null);
						int num=Tools.addDataTableNum(rs, model, 6);
						Tools.setTableSize(table, WIDTH, num);
						
						//将表当中的删除
						String data1[]= {numString};
						Mysqld.upDate("delete from users where account=?", data1);
						
						Tools.messageWindows("删除教师成功");
					}
				}
			}
		});
		
		//更改的功能
		jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name=jTextField.getText();
				String sexString;
				if(box1.isSelected()) {
					sexString="男";
				}else {
					sexString="女";
				}
				if(name.equals("")) {
					Tools.messageWindows("请输入姓名");
				}else if(cmb.getSelectedIndex()==0){
					Tools.messageWindows("请选择学院");
				}else if(cmb1.getSelectedIndex()==0){
					Tools.messageWindows("请选择学历");
				}else {
					
					String collegeString=(String)cmb.getSelectedItem();
					String edu=(String)cmb1.getSelectedItem();
					String data[]= {name,collegeString,edu,sexString,jTextField2.getText()};
					String string="update teacher set  name=?,oncollege=?, edu=? ,sex=? where id=?";
					int num=Mysqld.upDate(string, data);
					if(num==0) {
						Tools.messageWindows("请检查教师工号是否正确");
						
					}
					if(num==-1) {
						Tools.messageWindows("请检查教师工号格式是否正确！");
						
					}
					if(num==1) {
						Tools.messageWindows("教师信息更改成功");
						
					}
				}
			}});
		 
		  
		//____________________________________________________________________
	       new Timer().schedule(new TimerTask() {
	    	   int a=0;
	            @Override
	            public void run() {
	                try {
	                    //根据不同
	   
	                if(a==0) {
	                	  if(ManageWindows.jLabel3!=null&& ManageWindows.jLabel3.getForeground().equals(new Color(30,144,255))) {
		                		Tools.upDateComboBox("select * from college", cmb,"请选择学院","college");
		                		a=1;
		                }
	                }else {
	                	if(ManageWindows.jLabel3!=null&& ManageWindows.jLabel3.getForeground().equals(new Color(30,144,255))) {
	                		
	                		
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
