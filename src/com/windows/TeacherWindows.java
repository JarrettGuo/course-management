package com.windows;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

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
import com.ManageWindows.StudentMan;
import com.ManageWindows.TeacherMan;
import com.listen.ItemListenMouse;
import com.style.StyleFont;
import com.teacher.CouseMan;
import com.teacher.GetSoure;
import com.tools.EasyCode;
import com.tools.Mysqld;
import com.tools.Table;
import com.tools.Tools;

public class TeacherWindows {
	//定义一个界面
	JFrame jFrame=new JFrame();
	
	final int WIDTH=1022;
	final int HEIGHT=670;
	
	JLayeredPane jPanel3;
	Object obj[];
	String teacher;
	String name;
	
	
	public TeacherWindows(String teacher) {
		
		this.teacher=teacher;
		init();	
		jFrame.setResizable(false);//窗口是否可变
		jFrame.setVisible(true);//窗口是否可见
		jFrame.setDefaultCloseOperation(jFrame.DISPOSE_ON_CLOSE);//设置默认关闭方式
		jFrame.validate();//让组件生效

		//获取老师姓名
		ResultSet rs = Mysqld.QueryData("select name from teacher where id='"+teacher+"'",null);
		try {
			while(rs.next()){
				name = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		jFrame.setTitle("选课管理系统-老师-" + name);
		
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
		jPanel2.setBounds(10, 10, 200, HEIGHT-55-20);
		jPanel.add(jPanel2);
		
		
		Box boxOne;//用于存储图标
		boxOne=Box.createVerticalBox();
		jPanel2.add(boxOne);
		//______________________________________________
		//JLayeredPane jpanel2 = new JLayeredPane();
		//InStockPan inpan=new InStockPan(0, 0, 665+50, HEIGHT-10);
		//jpanel2.add(inpan, (Integer) (JLayeredPane.PALETTE_LAYER));
		//jpanel2.moveToFront(inpan);
		
		
		//___________________________________________________
		//将各个盘子进行分层
		JLayeredPane jPanel3 =new JLayeredPane();//定义一个千层饼
		jPanel3.setBounds(215, 10, WIDTH-215-25, HEIGHT-50-25);
		
		//添加学院管理
		CouseMan cousman=new CouseMan(0, 0, WIDTH-215-25, HEIGHT-55,teacher);
		jPanel3.add(cousman, (Integer) (JLayeredPane.PALETTE_LAYER));
		
		
		//成绩下发
		//下发成绩
		GetSoure divcouse=new GetSoure(0, 0, WIDTH-215-25, HEIGHT-55,teacher);
		jPanel3.add(divcouse, (Integer) (JLayeredPane.PALETTE_LAYER));
	

		
		
		
		Object obj[]= {cousman,divcouse};
		this.obj=obj;
		
		
		
		jPanel.add(jPanel3);
	
		JLabel jLabel=new JLabel("课程管理");
		jLabel.setForeground(new Color(150,150,150));
		boxOne.add(jLabel);
		
		
		
		
		JLabel jLabel2=new JLabel("申请选修课程");
		//jLabel2.setForeground(new Color(51,51,51));
		jLabel2.setFont(StyleFont.Item);
		jLabel2.setIcon(new ImageIcon("src/WindowsIcon/TIT1.png"));
		jLabel2.setForeground(new Color(30,144,255));
		boxOne.add(jLabel2);
		
		JLabel jLabel3=new JLabel("成绩分数管理");
		//jLabel2.setForeground(new Color(51,51,51));
		jLabel3.setFont(StyleFont.Item);
		jLabel3.setIcon(new ImageIcon("src/WindowsIcon/TIT2.png"));
		//jLabel3.setForeground(new Color(30,144,255));
		boxOne.add(jLabel3);

		jFrame.add(jPanel);
		
		//测试输出
		
		
		this.jPanel3=jPanel3;
		addItemListen(boxOne);
		ItemListenMouse.oldJLabel=jLabel2;//将老的标签存入
	
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
	//刷新


}
