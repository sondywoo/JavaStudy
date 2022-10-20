package com.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;

/*
 * 	特点：
 * 		采用硬连接 连接DB
 */
public class JdbcUtil1 {
	static{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
//			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static String url = "jdbc:mysql://localhost:3306/sondydb";
	private static String userName = "root";
	private static String password = "wsmsky";
//	private static String url = "jdbc:oracle:thin:@10.158.140.140:1521:FNLOC";
//	private static String userName = "system";
//	private static String password = "manager";
	
	public static Connection getConnection(){
		Connection con = null;
		try{
			con = DriverManager.getConnection(url, userName, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
  
	public static void printResultSet(ResultSet rs){
		if(rs == null){return;}
		try{
			//1.得到元数据对象
			ResultSetMetaData rsmd=rs.getMetaData();
			//2.得到字段个数 
			int cols=rsmd.getColumnCount();
			//3.根据字段个数遍历和打印结果集
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<cols;i++){
			sb.append("\t" + rsmd.getColumnName(i+1));
			}
			sb.append("\n\n");
			
			while(rs.next()){
				for(int i=0;i<cols;i++){
					sb.append("\t" + rs.getString(i+1));
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void close(Object o){
		if(o == null){ return;}
		try{
			if(o instanceof ResultSet){
				((ResultSet)o).close();
			}else if(o instanceof Statement){
				((Statement)o).close();
			}else if(o instanceof Connection){
				((Connection)o).close();
			}else if(o instanceof Context){
				((Context)o).close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection con){
		if(rs != null){ try{ rs.close(); }catch(Exception ex){ex.printStackTrace();}}
		if(stmt != null){ try{ stmt.close(); }catch(Exception ex){ex.printStackTrace();}}
		if(con != null){ try{ con.close(); }catch(Exception ex){ex.printStackTrace();}}
	}
	
	public static void releaseResources(Object... objects){
		try {
			for(Object obj:objects){
				if(obj instanceof ResultSet){
					((ResultSet) obj).close();
					System.out.println(obj.getClass().getName() + " is closed.");
				}
				if(obj instanceof Statement){
					((Statement) obj).close();
					System.out.println(obj.getClass().getName() + " is closed.");
				}
				if(obj instanceof Connection){
					((Connection) obj).close();
					System.out.println(obj.getClass().getName() + " is closed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
