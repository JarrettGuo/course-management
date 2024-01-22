package com.until;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBUtil {

	//定义一个可以调用连接
	public static Connection con=null;

	//定义变量
	String account;
	String password;
	String datbasName;
	
	//连接数据库
	//账号  密码  数据库 名字 
	public DBUtil(String account,String password,String datbasName) {//数据库的账号，数据库的密码 ，数据库的名字
		this.account = account;
		this.password = password;
		this.datbasName = datbasName;
	}

	public boolean connect(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功");

		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("加载驱动失败");
			e.printStackTrace();
		}

//		String url="jdbc:mysql://localhost:3306/"+"course"+"?characterEncoding=utf-8&useSSL=false";
		String url="jdbc:mysql://121.5.75.29:3306/"+"course"+"?characterEncoding=utf-8&useSSL=false";
		//连接数据库
		try {
			con=DriverManager.getConnection(url,account,password);
			System.out.println("连接数据库成功");
			return true;

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("连接数据库失败");
			return false;
		}
	}
}
