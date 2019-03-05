package com.jdbc.basic;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jdbc.util.JdbcUtil1;

public class WaysOfRegisterDriver {
	public static void main(String[] args){

		String dbDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/StudyDB";
		String userName = "root";
		String password = "admin";

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM t_jdbc_basic_student";
		
		try{
			//---------------------------------------------
			
			//	3 Ways of registering a driver: 
			
			//	1. Using the class loader (most common)
//			Class.forName(dbDriver); 
			
			//	2. Using the jdbc.drivers property (common)
			//		set 'VM Arguments' to '-Djdbc.drivers=com.mysql.jdbc.Driver'
			//		javac xxx.java
			//		java -Djdbc.drivers=com.mysql.jdbc.Driver xxx args[0] args[1] ...
			//		NOTICE: java 'VM Arguments' xxx 'Program Arguments'
			
			//	3. Instantiating a driver (less common)
			Driver drv = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(drv);
			
			//---------------------------------------------
						
			con = DriverManager.getConnection(url, userName, password);
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
