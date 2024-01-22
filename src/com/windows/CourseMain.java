package com.windows;

import com.until.DBUtil;

public class CourseMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginWindows loginWindows=new LoginWindows();
		
		DBUtil db=new DBUtil("course","coursedatabase","course");//数据库的账号 ，和数据的密码 和数据对的名字
		boolean DBStatus = db.connect();
		
	}

}
