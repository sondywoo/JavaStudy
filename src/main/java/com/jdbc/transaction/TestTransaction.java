package com.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.util.JdbcUtil1;

public class TestTransaction {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			con = JdbcUtil1.getConnection();
			if(con == null){
				System.out.println("Cannot connect with the DB!");
			}
			
			//	The steps of the JDBC Transaction:
			//	1. 关闭自动提交. con.setAutoCommit to FALSE to close the auto-commit. The default value is TRUE.
			con.setAutoCommit(false);
			
			//	2. 设置事务隔离级别.
			//	Oracle: Default value of transaction's isolation is "Connection.TRANSACTION_READ_COMMITTED"
			//	MySQL:  Default value of transaction's isolation is "Connection.TRANSACTION_REPEATABLE_READ"
			//con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			
			//	3. 执行数据库操作. Create Statement, and do all SQL operations.
//			ps = con.prepareStatement("INSERT INTO t_jdbc_basic_student VALUES (?, ?)");
//			ps.setInt(1, 4);
//			ps.setString(2, "La La Love");
//			ps.executeUpdate();
//			JdbcUtil1.close(ps);
			
			ps = con.prepareStatement("SELECT * FROM t_jdbc_basic_student ORDER BY stuid");
			rs = ps.executeQuery();
			JdbcUtil1.printResultSet(rs);
			
			//	4. 提交或回滚事务. Commit or Rollback it.
			con.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//	4. Commit or Rollback it.
			try {con.rollback();} catch (SQLException e) {e.printStackTrace();}
			JdbcUtil1.close(rs, ps, con);
		}
	}
}
