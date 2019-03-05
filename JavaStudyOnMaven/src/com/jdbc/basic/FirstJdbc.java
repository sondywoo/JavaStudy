package com.jdbc.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class FirstJdbc {
	static{
		try{
			//	1. Loading driver.
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/StudyDB";
		String userName = "root";
		String password = "admin";
		try{
			//	2. Connect with DB.
			con = DriverManager.getConnection(url, userName, password);
			
			//	3. Operate the DB.
			//	3.1 Execute SQL.
			stmt = con.createStatement();
//			int updatedNum = stmt.executeUpdate("INSERT INTO t_jdbc_basic_student VALUES ('4', 'WuSongmao')");
//			System.out.println(">>>>>>>>>>>>>>" + updatedNum);
			
			rs = stmt.executeQuery("SELECT * FROM t_jdbc_basic_student");
			
			//	3.2 Operate ResultSet.
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for(int i = 1; i<=columnCount; i++){
				System.out.print("\t" + rsmd.getColumnName(i));
			}
			System.out.println("\n");
			while(rs.next()){
				for(int i=1; i<=columnCount; i++){
					System.out.print("\t" + rs.getObject(i));
				}
				System.out.println();
			}
			
			//	MySQL: Default value of transaction's isolation is "Connection.TRANSACTION_REPEATABLE_READ"
			System.out.println(Connection.TRANSACTION_REPEATABLE_READ);
			System.out.println(con.getTransactionIsolation());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//	4. Close all resources refer to DB connection.
			//		NOTICE: 关闭顺序从小到大
			if(  rs != null){try{   rs.close(); }catch(Exception ex){ex.printStackTrace();}}
			if(stmt != null){try{ stmt.close(); }catch(Exception ex){ex.printStackTrace();}}
			if( con != null){try{  con.close(); }catch(Exception ex){ex.printStackTrace();}}
		}
	}
}
