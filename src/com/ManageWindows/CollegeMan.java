package com.ManageWindows;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.style.StyleFont;
import com.tools.*;


public class CollegeMan extends JPanel {

	int WIDTH;
	int HEIGHT;
	

	
	public CollegeMan(int x,int y,int width,int height) {
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
	
		
		//单选框
		
		 ButtonGroup g = new ButtonGroup();//建立一个组
		 JRadioButton box1 = new JRadioButton("学院ID",true);
		 JRadioButton box2 = new JRadioButton("学院名称",false);
	
		 box1.setOpaque(false);
		 box2.setOpaque(false);
		 boxOneBox.add(box1);
		 boxOneBox.add(box2);
		 g.add(box1);
		 g.add(box2);
		    
	
		
		
		
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JTextField jTextField=new JTextField(6);
		boxOneBox.add(jTextField);
		//jTextField.setFont(StyleFont.Item);
		
		JButton jButton=new JButton("查找学院");
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(jButton);
		
		
		JButton jButton2=new JButton("删除学院");
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(jButton2);
	
		
		JButton jButton3=new JButton("更改学院");
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox.add(jButton3);
		//另起一行增加学院
		boxH.add(Box.createVerticalStrut(20));//设置组件之间间隔 增加一个高20的合作
		
	
		
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JLabel jLabel1=new JLabel("学院名称");
		jLabel1.setFont(StyleFont.Item);
		boxOneBox.add(jLabel1);
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JTextField jTextField1=new JTextField(6);
		boxOneBox.add(jTextField1);
		//jTextField1.setFont(StyleFont.Item);
		
		
		
		JButton jButton1=new JButton("增加学院");
		boxOneBox.add(Box.createHorizontalStrut(6));//设置组件之间间隔
		boxOneBox.add(jButton1);

		
		
		//写表格
	
	
	
		
		this.add(boxH);
		
		//________________________________________________________
		Object columns[] ={"序号","学院编号","学院名字"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,180));//设置整个滚动条窗口的大小
		
		
		
		this.add(JS);
		//__________________________________________________________________
		
		JLabel jLabel2 =new JLabel ("____________________________________________________________________________________________________________________");
		this.add(jLabel2);
		
		
		Box boxH1=Box.createVerticalBox();
		Box boxOneBox1=Box.createHorizontalBox();//定义的是第一行
		
		boxH1.add(boxOneBox1);
		this.add(boxH1);
		
		JLabel jLabel3 =new JLabel("条件学院名称");
		jLabel3.setFont(StyleFont.Item);
		boxOneBox1.add(jLabel3);
		boxOneBox1.add(Box.createHorizontalStrut(10));
		
		JTextField jTextField2=new JTextField(6);
		//jTextField2.setFont(StyleFont.Item);
		boxOneBox1.add(jTextField2);
		
		JButton jButton4=new JButton("查找学院专业");
		boxOneBox1.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox1.add(jButton4);
		
		boxOneBox1.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JLabel jLabel4 =new JLabel("条件专业名称");
		jLabel4.setFont(StyleFont.Item);
		boxOneBox1.add(jLabel4);
		boxOneBox1.add(Box.createHorizontalStrut(10));
		
		JTextField jTextField3=new JTextField(6);
		//jTextField3.setFont(StyleFont.Item);
		boxOneBox1.add(jTextField3);
		
		JButton jButton5=new JButton("查找专业");
		boxOneBox1.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox1.add(jButton5);
		
		
		boxOneBox1.add(Box.createHorizontalStrut(10));
		
		
		JButton jButton7=new JButton("更改专业");
		boxOneBox1.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxOneBox1.add(jButton7);
		
		//jTextField6.setFont(StyleFont.Item);

		
		
		
		//增加  删除   更改 
		Box boxTwoBox=Box.createHorizontalBox();//定义的是第二行
		boxH1.add(Box.createVerticalStrut(20));//设置组件之间间隔 增加一个高20的合作
		boxH1.add(boxTwoBox);
		
		JLabel jLabel5=new JLabel("学院名称");
		jLabel5.setFont(StyleFont.Item);
		boxTwoBox.add(jLabel5);
		
		boxTwoBox.add(Box.createHorizontalStrut(10));
		JComboBox jComboBox =new JComboBox();
		jComboBox.addItem("--请选择学院--");
		boxTwoBox.add(jComboBox);
		
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		JLabel jLabel6 =new JLabel("【改-增-删】专业名称");
		jLabel6.setFont(StyleFont.Item);
		boxTwoBox.add(jLabel6);
		boxTwoBox.add(Box.createHorizontalStrut(10));
		
		JTextField jTextField4=new JTextField(8);
		//jTextField4.setFont(StyleFont.Item);
		boxTwoBox.add(jTextField4);
		
		
		JButton jButton6=new JButton("增加专业");
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxTwoBox.add(jButton6);
		
	
		
		JButton jButton8=new JButton("删除专业");
		boxTwoBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		boxTwoBox.add(jButton8);
		
		
		
		//________________________________________________________
		Object A[] ={"序号","专业ID","学院名字","专业名字"};
		Table B=new Table(A);
		JTable C = B.getTables();
		JScrollPane D = B.getJScrollPane();
		D.setPreferredSize(new Dimension(WIDTH-40,200));//设置整个滚动条窗口的大小
		DefaultTableModel model1 = B.getModel();
		this.add(D);
		//__________________________________________________________________

		//程序初始化加载
		Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
		
		//___________________________________________________________________
		//增加学院监听
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=jTextField1.getText();
				if(str.equals("")) {
					Tools.messageWindows("请输入学院名称");
				}else {
					String data[]=new String [1];//初始化一个数组
					data[0]=str;
					String sqlString="insert into college (college) values (?)";
					int num=Mysqld.upDate(sqlString, data);
					if(num==-1) {
						Tools.messageWindows("添加学院失败，请检查名称！");
					}else {
						
						//jComboBox
						//读取一下将东西写入数据库
						
						Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
						
						
						String strsqlString = "SELECT * from college where college=?";
						String data1[]=new String[1];
						data1[0]=str;
						ResultSet rs = Mysqld.QueryData(strsqlString,data1);
			
						int count=Tools.addDataTableNum(rs,model, 2);
						Tools.setTableSize(table, WIDTH, count);
						
						
						
						Tools.messageWindows("添加学院成功");
					}
					
				}
	
				
			}
		});
		
		
		//查询数据库的学院信息
		 jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id=jTextField.getText();//学院的ID
				
				String strsqlString;
				if(id.equals("")) {
					//查找全部
					strsqlString="select * from college";
					ResultSet rs = Mysqld.QueryData(strsqlString, null);
				
					int count=Tools.addDataTableNum(rs,model, 2);
					Tools.setTableSize(table, WIDTH, count);
					
				}else {
					if(box1.isSelected()) {
						//按ID查找
						strsqlString="SELECT * from college where ID=?";
						String data[]=new String[1];
						data[0]=id;
						ResultSet rs = Mysqld.QueryData(strsqlString,data);
			
						int count=Tools.addDataTableNum(rs,model, 2);
						Tools.setTableSize(table, WIDTH, count);
						
					}else {
						//按名称查找
						strsqlString="SELECT * from college where college=?";
						String data[]=new String[1];
						data[0]=id;
						ResultSet rs = Mysqld.QueryData(strsqlString,data);
					
						int count=Tools.addDataTableNum(rs,model, 2);
						Tools.setTableSize(table, WIDTH, count);
						
						
						
						
					}
					//按ID查找
				}
				
				
			}
		});
		
	//删除的按钮
		 jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id=jTextField.getText();//学院的ID
				if(!id.equals("")) {
					
					if(box1.isSelected()) {
						String strsqlString = "DELETE FROM college where id=?";
						String data[]=new String[1];
						data[0]=id;
						int num=Mysqld.upDate(strsqlString, data);
						if(num==-1) {
							Tools.messageWindows("请输入正确的学院ID");
						}else if(num==0){
							Tools.messageWindows("您查找的学院不存在");
							
						}else{

							
							String id1=jTextField.getText();//学院的ID
							jTextField.setText("");
							jButton.doClick();
							jTextField.setText(id1);
							
							Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
							Tools.messageWindows("删除成功");
							
							 
						}
						
					}else {
						
						String strsqlString = "DELETE FROM college where college=?";
						String data[]=new String[1];
						data[0]=id;
						int num=Mysqld.upDate(strsqlString, data);
						if(num==-1) {
							Tools.messageWindows("请输入正确的学院名称");
						}else if(num==0){
							Tools.messageWindows("您查找的学院不存在");
							
						}else {

							
							String id1=jTextField.getText();//学院的ID
							jTextField.setText("");
							jButton.doClick();
							jTextField.setText(id1);
							
							Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
							Tools.messageWindows("删除成功");
							 
						}
						
					}
				}else {
					
					Tools.messageWindows("删除内容不能为空，请查看左侧单选按钮是否与输入对应");
					
				}
			}
		});
		
		
	//代码的更改
	jButton3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String id=jTextField.getText();//学院的ID
			if(box1.isSelected()) {
				if(id.equals("")) {
					Tools.messageWindows("更内容不能为空，请查看左侧单选按钮是否与输入对应");
				}else {
					
					String string="update college set college=?  where id=?";
					
					String data[]= {jTextField1.getText(),id};
					
					int sta=Mysqld.upDate(string, data);
					if(sta==-1) {
						Tools.messageWindows("请输入正确格式的学院ID");
					}
					if(sta==0) {
						Tools.messageWindows("请输入正确的学院ID");
					}
					if(sta==1) {
						String strsqlString="SELECT * from college where ID=?";
						String data1[]=new String[1];
						data1[0]=id;
						ResultSet rs = Mysqld.QueryData(strsqlString,data1);
			
						int count=Tools.addDataTableNum(rs,model, 2);
						Tools.setTableSize(table, WIDTH, count);
						
						
						Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
						Tools.messageWindows("更改成功");
					}
					
				}
				
			}else {
				if(id.equals("")) {
					Tools.messageWindows("更内容不能为空，请查看左侧单选按钮是否与输入对应");
				}else {
				
					String string="update college set college=?  where college=?";
					String data[]= {jTextField1.getText(),id};
					
					int sta=Mysqld.upDate(string, data);
					if(sta==-1) {
						Tools.messageWindows("请检查输入的格式");
					}
					if(sta==0) {
						Tools.messageWindows("请输入正确的学院名称");
					}
					if(sta==1) {
						String strsqlString="SELECT * from college where college=?";
						String data1[]=new String[1];
						data1[0]=jTextField1.getText();
						
						ResultSet rs = Mysqld.QueryData(strsqlString,data1);
			
						int count=Tools.addDataTableNum(rs,model, 2);
						Tools.setTableSize(table, WIDTH, count);;

						
						Tools.upDateComboBox("select * from college", jComboBox,"请选择学院","college");
						Tools.messageWindows("更改成功");
					}
				}
			}
			
			
		
			
			
			
		}
	});
//增加专业
	
	jButton6.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(jComboBox.getSelectedIndex()==0) {
				Tools.messageWindows("请选择学院");
			}else {
				//向数据库写入数据
				String collegeString=(String) jComboBox.getSelectedItem();
				String majoString= jTextField4.getText();
				
				if(majoString.equals("")) {
					Tools.messageWindows("专业名称不能为空");
				}else {
					String sqlString="insert into major (college,proname )values (?,?)";
					String data[]= {collegeString,majoString};
					int sta=Mysqld.upDate(sqlString, data);
					if(sta==-1) {
						Tools.messageWindows("请检查专业名称是否冲突");
					}
					if(sta==1) {
						//查找一个的
						String sqlString1="select * from major where proname=?";
						String data1[]= {jTextField4.getText()};
						ResultSet rs = Mysqld.QueryData(sqlString1, data1);
						int count=Tools.addDataTableNum(rs, model1, 3);
						Tools.setTableSize(C, WIDTH, count);
						
						Tools.messageWindows("添加专业成功");
					}
					
				}
				
				
			}
		}
	});
	//按学院查找专业
	jButton4.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String college = jTextField2.getText();
			
			if( college.equals("")) {
				//查找全部
				String sqlString="select * from major";
				ResultSet rs = Mysqld.QueryData(sqlString, null);
				int count=Tools.addDataTableNum(rs, model1, 3);
				Tools.setTableSize(C, WIDTH, count);
			}else {
				//则查找单个
				String sqlString="select * from major where college=?";
				String data[]= {college};
				ResultSet rs = Mysqld.QueryData(sqlString, data);
				int count=Tools.addDataTableNum(rs, model1, 3);
				Tools.setTableSize(C, WIDTH, count);
				if(count==0) {
					Tools.messageWindows("["+college+"]名称有误或者学院没有专业！");
				}
				
			}
			
			
		}
	});
	//按专业名字查找专业
	jButton5.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String major = jTextField3.getText();
			
			if( major.equals("")) {
				//查找全部
				String sqlString="select * from major";
				ResultSet rs = Mysqld.QueryData(sqlString, null);
				int count=Tools.addDataTableNum(rs, model1, 3);
				Tools.setTableSize(C, WIDTH, count);
			}else {
				//则查找单个
				String sqlString="select * from major where proname=?";
				String data[]= {major};
				ResultSet rs = Mysqld.QueryData(sqlString, data);
				int count=Tools.addDataTableNum(rs, model1, 3);
				Tools.setTableSize(C, WIDTH, count);
				if(count==0) {
					Tools.messageWindows("["+major+"]名称有误");
				}
				
			}
		}
	});
	//删除专业
	
	
	jButton8.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(jComboBox.getSelectedIndex()==0) {
				Tools.messageWindows("请选择学院");
			}else {
				//向数据库写入数据
				String collegeString=(String) jComboBox.getSelectedItem();
				String majoString= jTextField4.getText();
				
				if(majoString.equals("")) {
					Tools.messageWindows("专业名称不能为空");
				}else {
					
					String sqlString="DELETE from major where college=? and proname=?";
					String data[]= {collegeString,majoString};
					int sta=Mysqld.upDate(sqlString, data);
					if(sta==-1) {
						Tools.messageWindows("请检查专业名称是否正确");
					}
					if(sta==1) {
						//查找一个的
						String sqlString1="select * from major";
						ResultSet rs = Mysqld.QueryData(sqlString1, null);
						int count=Tools.addDataTableNum(rs, model1, 3);
						Tools.setTableSize(C, WIDTH, count);
						
						
						Tools.messageWindows("删除专业成功");
					}
					
				}
				
				
			}
		}
	});
	
	//更改专业
	jButton7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(jComboBox.getSelectedIndex()==0) {
				Tools.messageWindows("请选择学院");
			}else {
				//向数据库写入数据
				String collegeString=(String) jComboBox.getSelectedItem();
				String majoString= jTextField3.getText();
				String newMa= jTextField4.getText();
				if(majoString.equals("")) {
					Tools.messageWindows("条件专业名称不能为空");
				}else {
					
					if(newMa.equals("")) {
						Tools.messageWindows("专业名称不能为空");
					}else {
						String sqlString="update major set  proname=? where  college=? and proname=?";
						String data[]= {newMa,collegeString,majoString};
						int sta=Mysqld.upDate(sqlString, data);
						if(sta==-1) {
							Tools.messageWindows("请检更专业专业名称是否正确");
						}
						if(sta==1) {
							//查找一个的
							String sqlString1="select * from major";
							ResultSet rs = Mysqld.QueryData(sqlString1, null);
							int count=Tools.addDataTableNum(rs, model1, 3);
							Tools.setTableSize(C, WIDTH, count);
							
							
							Tools.messageWindows("更改专业成功");
						}
						
						if(sta==0) {
					
							
							
							Tools.messageWindows("更改失败,请检查学院名称是否对应");
						}
						
					}
				
					
				}
				
				
			}
		}
	});
	
	
//___________________________________________________________________
	}


}
