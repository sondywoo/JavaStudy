package com.jdbc.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.jdbc.util.JdbcUtil1;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import oracle.jdbc.pool.OracleDataSource;

public class JndiBind {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context ctx = null;
		try{
			//	在ＪＮＤＩ服务器中创建一个数据源　步骤：　
			
			//	1. 连接JNDI服务器
			System.out.print("Connecting with the JNDI Server......");
			Hashtable env=new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");// jndi\fscontext.jar
			env.put(Context.PROVIDER_URL, "file:/Project/workspace/javastudy");// jndi\providerutil.jar // 创建一个Jndi Bind文件的位置, 不区分大小写
			ctx = new InitialContext(env);
			System.out.println("SUCCESS");
			
			//	2. 创建一个MySQL的数据源对象
			System.out.print("Creating a special datasource......");
			MysqlDataSource mysqlDS = new MysqlDataSource();
			//	Method 1
			mysqlDS.setUrl("jdbc:mysql://localhost:3306/StudyDB");	//	<=>
			//	Method 2
//			mysqlDS.setServerName("localhost");
//			mysqlDS.setPortNumber(3306);
//			mysqlDS.setDatabaseName("StudyDB");
			
			mysqlDS.setUser("root");
			mysqlDS.setPassword("admin");
//			//	2. 创建一个Oracle的数据源对象
//			OracleDataSource oracleDS=new OracleDataSource();
//			oracleDS.setDriverType("thin");
//			oracleDS.setServerName("192.168.0.203");
//			oracleDS.setNetworkProtocol("tcp");
//			oracleDS.setDatabaseName("tarena");
//			oracleDS.setPortNumber(1521);
//			oracleDS.setUser("asd0706");
//			oracleDS.setPassword("asd0706");
			System.out.println("SUCCESS");
			
			//	3. 将该MySQL数据源对象存入JNDI服务器中
			System.out.print("Binding the datasource to the JNDI server......");
			ctx.bind("MysqlDSJdbc", mysqlDS);
			System.out.println("SUCCESS");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//	4. 关闭JNDI连接
			System.out.print("Shutting down the connection with the JNDI Server......");
			JdbcUtil1.close(ctx);
//			if(ctx != null){
//				try{ctx.close();}catch(Exception e){e.printStackTrace();}
//			}
			System.out.println("SUCCESS");
		}
	}
}
