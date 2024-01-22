package com.teacher;

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

import javax.management.DynamicMBean;
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
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.table.DefaultTableModel;

import com.style.StyleFont;
import com.tools.DataOther;
import com.tools.Mysqld;
import com.tools.Table;
import com.tools.Tools;

public class GetSoure extends JPanel{
	int WIDTH;
	int HEIGHT;
	String TeaName;
	public GetSoure(int x,int y,int width,int height,String TeaName) {
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
		
		initJL("课程名称",boxOneBox);
	
		ResultSet rs = Mysqld.QueryData("select  name,oncollege from teacher where id='"+TeaName+"'",null);
	
		String data[]=	getSqlData(rs,2);

		JComboBox cmb=new JComboBox();
		cmb.addItem("--课程选择--");
		String tempString="select * from optcous where   teacher='"+data[0]+"'and college='"+data[1]+"'";
		Tools.upDateComboBox(tempString, cmb, "课程选择", "coursname");
		
		boxOneBox.add(cmb);
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JCheckBox jCheckBox=new JCheckBox("已审批");
		boxOneBox.add(jCheckBox);
		jCheckBox.setOpaque(false);
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		
		JCheckBox jCheckBox1=new JCheckBox("未审批");
		boxOneBox.add(jCheckBox1);
		boxOneBox.add(Box.createHorizontalStrut(10));//设置组件之间间隔
		jCheckBox1.setOpaque(false);
		
		JButton jButton=new JButton("查询学生");
		boxOneBox.add(jButton);
		
		
		
		
		//____________________________________________________________________
		Object columns[] ={"序号","学院","专业","课程名称","分数","学号"};
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension(WIDTH-40,540));//设置整个滚动条窗口的大小
		this.add(JS);
		
	
		
		
		//推选
		//Tools.addDataTableNum(Mysqld.QueryData("select college,pro,optcouse,IF(ISNULL(count)=1,'',count),stunum from studengcours where ", null), model, 5);
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			if(	cmb.getSelectedIndex()==0) {
				Tools.messageWindows("请选择课程");
				
				
				
			}else {
				
				
				ResultSet rs = Mysqld.QueryData("select  name,oncollege from teacher where id='"+TeaName+"'",null);
				
				String data[]=	getSqlData(rs,2);
	
					
				
				String string=(String) cmb.getSelectedItem();//课程名称
				data=conNect(data,string);
				if(jCheckBox.isSelected()==false&&jCheckBox1.isSelected()==false) {
					jCheckBox.setSelected(true);
				}
				
				//

				
				
				rs=	Mysqld.QueryData("select id from optcous where teacher=? and college=? and coursname=?", data);
				data=	getSqlData(rs,1);
				
			//	for(int i=0;i<data.length;i++) {
				
					//System.out.print(data[i]+"\t\n");
			//	}
				if(jCheckBox.isSelected()==true&&jCheckBox1.isSelected()==false) {
					
					rs=	Mysqld.QueryData("select college,pro,optcouse,IF(ISNULL(count)=1,'',count),stunum from studengcours where    cousid=? and ISNULL(count)=0", data);
				}else
				if(jCheckBox.isSelected()==false&&jCheckBox1.isSelected()==true){
					rs=	Mysqld.QueryData("select college,pro,optcouse,IF(ISNULL(count)=1,'',count),stunum from studengcours where    cousid=? and ISNULL(count)=1", data);
				}else if(jCheckBox.isSelected()==true&&jCheckBox1.isSelected()==true) {
					rs=	Mysqld.QueryData("select college,pro,optcouse,IF(ISNULL(count)=1,'',count),stunum from studengcours where    cousid=? ", data);
				}
				
			
			
				Tools.setTableSize(table, WIDTH, Tools.addDataTableNum(rs, model,5));
				
				
			}
			
			
	
			}
		});
	//分配成绩
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				String inputValue = JOptionPane.showInputDialog("请输入学生成绩");
				String numString=	(String) table.getValueAt(table.getSelectedRow(), 5);//学号
				
				//查询课程ID
				ResultSet rs = Mysqld.QueryData("select  name,oncollege from teacher where id='"+TeaName+"'",null);
				String data[]=	getSqlData(rs,2);
				String string=(String) table.getValueAt(table.getSelectedRow(), 3);
				data=conNect(data,string);
				
				

				rs=	Mysqld.QueryData("select id from optcous where teacher=? and college=? and coursname=? ", data);
				data=getSqlData(rs,1);
				
				string=(String) table.getValueAt(table.getSelectedRow(), 5);
				
				String strsqlString="update studengcours set count='"+inputValue+ "'where cousid='"+data[0]+"'and stunum='"+string+"'";

				Mysqld.upDate(strsqlString,null);
				
			
				
				jButton.doClick();
				
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
		//刷新
		cmb.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
        		
       
        		
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				JComboBox cmbcm=	(JComboBox)e.getSource();
				
				//查询全部
            	ResultSet rs = Mysqld.QueryData("select  name,oncollege from teacher where id='"+TeaName+"'",null);
       
        		String data[]=	getSqlData(rs,2);
        		JComboBox cmb=new JComboBox();
        		cmb.addItem("--课程选择--");
        		String tempString="select * from optcous where   teacher='"+data[0]+"'and college='"+data[1]+"'";
        	
        		Tools.upDateComboBox(tempString, cmbcm, "课程选择", "coursname");
        		
       
        		
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
				JComboBox cmbcm=	(JComboBox)e.getSource();
				//查询全部
            	ResultSet rs = Mysqld.QueryData("select  name,oncollege from teacher where id='"+TeaName+"'",null);
            	
        		String data[]=	getSqlData(rs,2);

        		JComboBox cmb=new JComboBox();
        		cmb.addItem("--课程选择--");
        		String tempString="select * from optcous where   teacher='"+data[0]+"'and college='"+data[1]+"'";
        	
        		Tools.upDateComboBox(tempString, cmbcm, "课程选择", "coursname");
        		
       
        		
			}
		});
		
		
		
		
		
		
		

		


	//			inputValue就是用户输入的值； 
		//刷新
		

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
