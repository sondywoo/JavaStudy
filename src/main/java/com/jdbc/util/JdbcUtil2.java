package com.jdbc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 	特点：
 * 		1. 采用软连接 连接DB，所有的连接参数都从配置文件"resource/dbConfig.properties"中读取
 * 		2. 也可采用JNDI Server中的数据源获得DB连接
 */
public class JdbcUtil2 extends JdbcUtil1{
	private static Properties dbConfig = new Properties();
	private static boolean autoCreateJndiDatasource;
	static{
		System.out.println("static in JdbcUtil2");
		try{
			autoCreateJndiDatasource = false;
			InputStream is = JdbcUtil2.class.getClassLoader().getResourceAsStream("resource/dbConfig.properties");
			dbConfig.load(is);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	 * Create a JDBC Connection.
	 */
	public static Connection getConnection(){
		Connection con = null;
		try{
			Class.forName(dbConfig.getProperty("dbDriver"));
			con = DriverManager.getConnection(dbConfig.getProperty("url"), dbConfig.getProperty("username"), dbConfig.getProperty("password"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
	/*
	 * Create a JDBC Connection.
	 */
	public static Connection getJdbcConnection(){
		return getConnection();
	}
	/*
	 * Get a Connection from the JNDI server.
	 */
	public static Connection getJndiConnection(){
		Connection con = null;
		try{
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");	//	jndi\fscontext.jar
			env.put(Context.PROVIDER_URL, dbConfig.getProperty("jndiBindfileUrl"));					//	jndi\providerutil.jar
			Context ctx = new InitialContext(env);
			
			//DataSource ds = (DataSource) ctx.lookup(dbConfig.getProperty("jndiDatasourceName"));
			DataSource ds = null;
			try{
				ds = (DataSource) ctx.lookup(dbConfig.getProperty("jndiDatasourceName"));
			}catch(NameNotFoundException ex){	// 如果JNDI服务器中没有所要找的数据源
				if(autoCreateJndiDatasource){	// 允许自动创建一个JNDI数据源对象
					ds = createJndiDatasource();
					ctx.rebind(dbConfig.getProperty("jndiDatasourceName"), ds);
				}else{							// 如果不允许自动创建JNDI数据源对象
					throw ex;
				}
			}
			con = ds.getConnection();
			return con;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * Set if it's allowed to auto create a JNDI datasource.
	 * TRUE  - DO allowed to auto create a JNDI datasource if it's NOT existed.
	 * FALSE - DO NOT allowed to auto create a JNDI datasource if it's NOT existed.
	 */
	public static void setAutoCreateJndiDatasource(boolean isAutoCreated){
		autoCreateJndiDatasource = isAutoCreated;
	}
	/*
	 * Create a JNDI datasource.
	 */
	private static DataSource createJndiDatasource(){
		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setUrl(dbConfig.getProperty("url"));
		mysqlDS.setUser(dbConfig.getProperty("username"));
		mysqlDS.setPassword(dbConfig.getProperty("password"));
		return mysqlDS;
	}
}
