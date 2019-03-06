package com.jdbc.blobClob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcUtil2;

public class WriteClob {

	/**
	 * ********************************************************************有问题！！！！！！！！
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			int previousObjectId = 3;
//			String fileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/TestBlob.jpg";
			String fileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/TestBlob.docx";
			Clob clob = null;
			
			con = JdbcUtil2.getConnection();
			if(con != null){
				con.setAutoCommit(false);
	
				/* ------------------------------------
				 * for MySQL & Oracle:
				 * ------------------------------------
				 */
				//	1. 
				File file = new File(fileName);
				FileReader fr = new FileReader(file);
//				FileInputStream fis = new FileInputStream(file);
				System.out.println("file size = " + fr.getEncoding().length());
				fr.close();
				
				//	2. Write the CLOB object to DB
				ps = con.prepareStatement("insert into T_JDBC_CLOB(ob_id, ob_name, ob_clob) values (?, ?, ?)");
				ps.setInt(1, previousObjectId);
				ps.setString(2, fileName);
				ps.setCharacterStream(3, fr, fr.getEncoding().length());
//				ps.setBinaryStream(3, fis, fis.available());
				ps.executeUpdate();
				JdbcUtil2.close(ps);
				
				con.commit();
			}
		}catch(Exception e){
			e.printStackTrace();
			if(con != null){
				try {
					con.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			JdbcUtil2.close(rs, ps, con);
		}
	}
}
