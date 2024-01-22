package com.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ManageWindows.CollegeMan;
import com.ManageWindows.Course;
import com.ManageWindows.DivateCourse;
import com.ManageWindows.GetSoureMan;
import com.ManageWindows.StudentMan;
import com.ManageWindows.TeacherMan;
import com.listen.ItemListenMouse;
import com.style.StyleFont;
import com.tools.EasyCode;
import com.tools.Table;
import com.tools.Tools;

public class ManageWindows {
	//定义一个界面
	JFrame jFrame=new JFrame();
	
	final int WIDTH=1022;
	final int HEIGHT=670;
	
	JLayeredPane jPanel3;
	Object obj[];
	
	
	
	public static JLabel jLabel2;
	public static JLabel jLabel3;
	public static JLabel jLabel4;
	public static JLabel jLabel6;
	public static JLabel jLabel7;
	public ManageWindows() {
		
		init();	
		jFrame.setResizable(false);//窗口是否可变
		jFrame.setVisible(true);//窗口是否可见
		jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jFrame.validate();//让组件生效
		jFrame.setTitle("选课管理系统-管理员");
	}
	
	void init() {
		
		//设置窗口在屏幕中间  并设置大小
		Tools.setWindowPos(WIDTH, HEIGHT, jFrame);
		EasyCode.setTileIcon(jFrame);//设置标题和图标
		jFrame.setLayout(null);
		jFrame.setBackground(Color.blue);
		
		JPanel jPanel =new JPanel();
		jPanel.setBackground(new Color(66,99,116));
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, WIDTH, HEIGHT);
		
		
		
		
		//定义第一个盘子
		JPanel jPanel2 =new JPanel();
		jPanel2.setBackground(new Color(255,255,255));
		jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel2.setBounds(10, 10, 200, HEIGHT-75);
		jPanel.add(jPanel2);
		
		
		Box boxOne;//用于存储图标
		boxOne=Box.createVerticalBox();
		jPanel2.add(boxOne);
		//______________________________________________
		//JLayeredPane jpanel2 = new JLayeredPane();
		//InStockPan inpan=new InStockPan(0, 0, 665+50, HEIGHT-10);
		//jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));
		//jpanel2.moveToFront(inpan);
		
		
		JMenuBar menubar = new JMenuBar();//创建一个菜单条
		JMenu menu = new JMenu("账号");
		menu.setIcon(new  ImageIcon("src/WindowsIcon/ITIT2.png"));
		JMenuItem item2_2 = new JMenuItem("注销账号",new  ImageIcon("src/WindowsIcon/ITIT.png"));
		JMenuItem item2_3 = new JMenuItem("退出登录",new  ImageIcon("src/WindowsIcon/ITIT1.png"));
		menu.add(item2_2);
		menu.add(item2_3);
		menubar.add(menu);
		jFrame.setJMenuBar(menubar);
		
		
		item2_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.dispose();
				LoginWindows loginWindows=new LoginWindows();
				
			}
		});
		
		//退出
		item2_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jFrame.dispose();
				
				
			}
		});
		
		
		
		

		
	
		
		
		
		
		
		//___________________________________________________
		//将各个盘子进行分层
		JLayeredPane jPanel3 =new JLayeredPane();//定义一个千层饼
		jPanel3.setBounds(215, 10, WIDTH-215-25, HEIGHT-50-25);
		
		//添加学院管理
		CollegeMan collegeman=new CollegeMan(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(collegeman, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//添加老师管理
		TeacherMan teacher=new TeacherMan(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(teacher, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//添加学生
		
		StudentMan student=new StudentMan(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(student, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//选课申请列表
		
		Course cours=new Course(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(cours, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		
		//分配课程列表
		DivateCourse drivate=new DivateCourse(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(drivate, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		//查看学生分数GetSoureMan
		GetSoureMan getcos=new GetSoureMan(0, 0, WIDTH-215-25, HEIGHT-55);
		jPanel3.add(getcos, (Integer) (JLayeredPane.PALETTE_LAYER));

		

		
		
		
		Object obj[]= {collegeman,teacher,student,cours,drivate,getcos};
		this.obj=obj;
		
		
		
		jPanel.add(jPanel3);
	
		JLabel jLabel=new JLabel("学院管理");
		jLabel.setForeground(new Color(150,150,150));
		boxOne.add(jLabel);
		
		
		
		
		jLabel2=new JLabel("学院与专业信息管理");
		//jLabel2.setForeground(new Color(51,51,51));
		jLabel2.setFont(StyleFont.Item);
		jLabel2.setIcon(new ImageIcon("src/WindowsIcon/IT1.png"));
		jLabel2.setForeground(new Color(30,144,255));
		boxOne.add(jLabel2);
		this.jLabel2=jLabel2;
		
		JLabel jLabel1=new JLabel("账号信息管理");
		jLabel1.setForeground(new Color(150,150,150));
		boxOne.add(jLabel1);
		
		JLabel jLabel3=new JLabel("老师账号信息管理");
		jLabel3.setForeground(new Color(51,51,51));
		jLabel3.setFont(StyleFont.Item);
		jLabel3.setIcon(new ImageIcon("src/WindowsIcon/IT2.png"));
		boxOne.add(jLabel3);
		this.jLabel3=jLabel3;
		
		JLabel jLabel4=new JLabel("学生账号信息管理");
		jLabel4.setForeground(new Color(51,51,51));
		jLabel4.setFont(StyleFont.Item);
		jLabel4.setIcon(new ImageIcon("src/WindowsIcon/IT3.png"));
		boxOne.add(jLabel4);
		this.jLabel4=jLabel4;
		
		
	
		JLabel jLabel5=new JLabel("专业课程管理");
		jLabel5.setForeground(new Color(150,150,150));
		boxOne.add(jLabel5);
		
		JLabel jLabel6=new JLabel("选课申请与分配管理");
		jLabel6.setForeground(new Color(51,51,51));
		jLabel6.setFont(StyleFont.Item);
		jLabel6.setIcon(new ImageIcon("src/WindowsIcon/TIT1.png"));
		boxOne.add(jLabel6);
		this.jLabel6=jLabel6;
		
		
		JLabel jLabel7=new JLabel("分配选课管理");
		jLabel7.setForeground(new Color(51,51,51));
		jLabel7.setFont(StyleFont.Item);
		jLabel7.setIcon(new ImageIcon("src/WindowsIcon/IT4.png"));
		boxOne.add(jLabel7);
		this.jLabel7=jLabel7;
		
		
		//课程分数查询GetSoureMan
		JLabel jLabel8=new JLabel("选课查询");
		jLabel8.setForeground(new Color(51,51,51));
		jLabel8.setFont(StyleFont.Item);
		jLabel8.setIcon(new ImageIcon("src/WindowsIcon/TIT3.png"));
		boxOne.add(jLabel8);
		
		
		
		
		
		
		
		
		
		jFrame.add(jPanel);
		
		//测试输出
		
		
		this.jPanel3=jPanel3;
		addItemListen(boxOne);
		ItemListenMouse.oldJLabel=jLabel2;//将老的标签存入
	
	
		
		
	

	}
	void addItemListen(Box boxOne) {
		
		Component[] JLS =boxOne.getComponents();
		int j=0;
		for(int i=0;i<JLS.length;i++) {
			
			
			JLabel jLabel=(JLabel)JLS[i];
			Color color = new Color(150,150,150);
			
			if(!(jLabel.getForeground().equals(color))) {
				jLabel.addMouseListener(new ItemListenMouse(jPanel3,obj[j]));
				
				j++;
			}
		}
		
	}
	//
}
