package com.jdbc.jndi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import com.jdbc.util.JdbcUtil1;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JndiLookup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context ctx = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//	从ＪＮＤＩ服务器中获取一个数据源　步骤：　
			
			//	1. 连接JNDI服务器
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
			env.put(Context.PROVIDER_URL, "file:/Project/workspace/javastudy");	//	要读取的Jndi Bind文件的位置， 不区分大小写
			ctx = new InitialContext(env);	//	必须要制定环境env
//			ctx = new InitialContext();		// 	ERROR
			System.out.println("Connecting with the JNDI Server......SUCCESS");
			
			//	2. 获取某一个数据源对象
			//	Method 1: 如果JNDI服务器中没有所要找的数据源就报错
			//DataSource ds = ctx.lookup("MysqlDSJdbc");
			//	Method 2: 如果JNDI服务器中没有所要找的数据源就重新创建一个MySQL数据源，并存入JNDI服务器中
			DataSource ds = null;
			try{
				ds = (DataSource) ctx.lookup("MysqlDSJdbc");
			}catch(NameNotFoundException ex){
				MysqlDataSource mysqlDS = new MysqlDataSource();
				mysqlDS.setUrl("jdbc:mysql://localhost:3306/StudyDB");
				mysqlDS.setUser("root");
				mysqlDS.setPassword("admin");
				ctx.rebind("MysqlDSJdbc", mysqlDS);
				ds = mysqlDS;
			}
			
			System.out.println("Looking up a datasource from the JNDI Server......SUCCESS");
			
			JdbcUtil1.close(ctx);
//			ctx.close();
			System.out.println("Shutting down the connection with the JNDI Server......SUCCESS");
			
			//	3. 通过该数据源取得Connection，进行数据库操作
			con = ds.getConnection();
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
