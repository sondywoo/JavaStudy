package com.jdbc.blobClob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.util.JdbcUtil1;
import com.jdbc.util.JdbcUtil2;

public class ReadClob {

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
//			String newFileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/new2TestBlob.jpg";
			String newFileName = "E:/Project/Workspace/JavaStudy/src/com/jdbc/blobClob/newTestClob.docx";
			Clob clob = null;
			
			con = JdbcUtil2.getConnection();
			if(con != null){
				con.setAutoCommit(false);
				
				//	1. Read the CLOB object from DB.
				ps = con.prepareStatement("select ob_clob from T_JDBC_CLOB where ob_id = ?");
				ps.setInt(1, previousObjectId);
				rs = ps.executeQuery();
				if(rs.next()){
					clob = rs.getClob(1);
				}
				
				//	2. Copy it to the new file.
				Writer writer = new FileWriter(newFileName);
				Reader reader = clob.getCharacterStream();
				char[] data = new char[10];
				for(int i = reader.read(data); i != -1; i = reader.read(data)){
					System.out.print(data);
					writer.write(data);
				}
				reader.close();
				writer.close();
				
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
