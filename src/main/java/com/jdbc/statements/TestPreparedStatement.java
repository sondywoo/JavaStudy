package com.jdbc.statements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.util.JdbcUtil1;

public class TestPreparedStatement {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JdbcUtil1.getConnection();
			ps = con.prepareStatement("INSERT INTO t_jdbc_basic_student VALUES (?, ?)");
			ps.setInt(1, 5);
			ps.setString(2, "Dream");
			ps.executeUpdate();
			
			ps = con.prepareStatement("UPDATE t_jdbc_basic_student SET stuname=? WHERE stuid=?");
			ps.setInt(2, 3);
			ps.setString(1, "Michael");
			ps.executeUpdate();
			
			ps = con.prepareStatement("SELECT * FROM t_jdbc_basic_student");
			rs = ps.executeQuery();
			JdbcUtil1.printResultSet(rs);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil1.close(rs, ps, con);
		}
	}
}
