package com.jdbc.blobClob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcUtil2;

public class ReadBlob {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			int previousObjectId = 3;
//			String newFileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/new2TestBlob.jpg";
			String newFileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/newTestBlob.docx";
			Blob blob = null;
			
			con = JdbcUtil2.getConnection();
			if(con != null){
				con.setAutoCommit(false);
				
				//	1. Read the BLOB object from DB.
				ps = con.prepareStatement("select ob_blob from T_JDBC_BLOB where ob_id = ?");
				ps.setInt(1, previousObjectId);
				rs = ps.executeQuery();
				if(rs.next()){
					blob = rs.getBlob(1);
				}
				
				//	2. Copy it to the new file.
				OutputStream os = new FileOutputStream(newFileName);
				InputStream is = blob.getBinaryStream();
				byte[] data = new byte[is.available()];
				for(int i=is.read(); i != -1;i=is.read()){
					os.write(i);
				}
				is.close();
				os.close();
				
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
