package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.jdbc.util.JdbcUtil1;

public class WaysOfConnection {

	public static void main(String[] args) {
		String dbDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/StudyDB";
		String userName = "root";
		String password = "admin";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM t_jdbc_basic_student";
		
		try{
			Class.forName(dbDriver);

			//---------------------------------------------
			
			//	2 Ways of getting the DB connection: 
			
			//	1. 
//			con = DriverManager.getConnection(url, userName, password);
			
			//	2. Properties中必须包括"user"和"password", 而且名字也不能更改成其他的
			Properties userInfo = new Properties();
			userInfo.setProperty("lalala", sql);
			userInfo.setProperty("user", userName);
			userInfo.setProperty("password", password);
			con = DriverManager.getConnection(url, userInfo);
			
			//---------------------------------------------
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			JdbcUtil1.printResultSet(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil1.close(rs, stmt, con);
		}
	}
}
