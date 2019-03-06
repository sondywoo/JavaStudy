package com.jdbc.blobClob;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcUtil2;

public class WriteBlob {

	/**
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
			Blob blob = null;
			
			con = JdbcUtil2.getConnection();
			if(con != null){
				con.setAutoCommit(false);
	
				/* ------------------------------------
				 * for MySQL & Oracle:
				 * ------------------------------------
				 */
				//	1. 
				File file = new File(fileName);
				FileInputStream fis = new FileInputStream(file);
				System.out.println("file size = " + fis.available());
				fis.close();
				
				//	2. Write the BLOB object to DB
				ps = con.prepareStatement("insert into T_JDBC_BLOB(ob_id, ob_name, ob_blob) values (?, ?, ?)");
				ps.setInt(1, previousObjectId);
				ps.setString(2, fileName);
				ps.setBinaryStream(3, fis, fis.available());
//				ps.setBlob(3, blob);
				ps.executeUpdate();
				JdbcUtil2.close(ps);

				
				
//				//	1. 
//				ps = con.prepareStatement("insert into T_JDBC_BLOB(ob_id, ob_name, ob_blob) values (?, ?, empty_blob())");
//				ps.setInt(1, 1);
//				ps.setString(2, fileName);
//				ps.executeUpdate();
//				JdbcUtil2.close(ps);
//				
//				//	2. 
//				ps = con.prepareStatement("select ob_blob from T_JDBC_BLOB where ob_name = ? for update");
//				ps.setString(1, fileName);
//				rs = ps.executeQuery();
//				if(rs.next()){
//					blob = rs.getBlob(1);
//				}
////				JdbcUtil2.close(ps);
//				
//				//	3. 
//				File file = new File(fileName);
//				FileInputStream fis = new FileInputStream(file);
//				System.out.println("file size = " + fis.available());
//				OutputStream os = blob.setBinaryStream(0);
//				byte[] data = new byte[(int)fis.available()];
//				fis.read(data);
//				os.write(data);
//				fis.close();
//				os.close();
//				
//				//	4. 
//				ps = con.prepareStatement("update T_JDBC_BLOB set ob_blob = ? where ob_name = ?");
//				ps.setBlob(1, blob);
//				ps.setString(2, fileName);
//				ps.executeUpdate();
//				JdbcUtil2.close(ps);
				
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
