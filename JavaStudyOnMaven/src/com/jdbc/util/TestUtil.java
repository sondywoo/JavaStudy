package com.jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestUtil {
	@Test
	public void testJdbcConnect(){
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
//		String sql = "SELECT * FROM t_jdbc_basic_student";
		String sql = "SELECT * FROM t_users";
		
		try{
			con = JdbcUtil2.getConnection();		// SUCCESS
			
//			JdbcUtil2.setAutoCreateJndiDatasource(true);
//			con = JdbcUtil2.getJndiConnection();	// SUCCESS

//			con = JdbcUtil3.getXAConnection();	// what's wrong???
//			asdfa
			
			System.out.println(con);
			
			if(con == null){
				return;
			}
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
