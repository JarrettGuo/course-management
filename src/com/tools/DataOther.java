package com.tools;

import java.security.interfaces.RSAKey;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataOther {
	//用于设置课程数据状态
	public static void setCoursSta(String sql,String data[]) {
		int num  = Mysqld.upDate(sql, data);
		if(num==0) {
			Tools.messageWindows("请检查课程名称是否正确");
		}else {
			Tools.messageWindows("课程退选成功");
		}
	}
	//查询课程状态 
	public static String showCoursSta(String sql,String data[]) {
		 ResultSet rs = Mysqld.QueryData(sql, data);
		 try {
			while(rs.next()) {
				return rs.getString("star");
			 }
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
}
