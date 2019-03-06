package com.jdbc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;

/**
 * 	特点：
 * 		1. 支持分布式事务管理 (JTA, Java Transaction API)
 */
public class JdbcUtil3 {//extends JdbcUtil2{
	private static Properties dbConfig = new Properties();
	static{
		try{
			InputStream is = JdbcUtil3.class.getClassLoader().getResourceAsStream("resources/dbConfig.properties");
			System.out.println(dbConfig);
			dbConfig.load(is);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * Create a JDBC Connection which supports JTA.
	 */
	public static Connection getXAConnection(){
		Connection con = null;
		try{
			AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
			ds.setUniqueResourceName(String.valueOf(Math.random()));//RandomCodeFactory.genRandomCode(9));
//			ds.setTestQuery("select 1 from dual");
			ds.setXaDataSourceClassName(dbConfig.getProperty("xaDataSource"));
			Properties p = new Properties();
			p.setProperty("user", 		dbConfig.getProperty("username"));
			p.setProperty("password", 	dbConfig.getProperty("password"));
			p.setProperty("URL", 		dbConfig.getProperty("url"));
			ds.setXaProperties(p);
			ds.setPoolSize(2);
			con = ds.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	public static UserTransaction getUserTransaction() {
		UserTransaction utx = new UserTransactionImp();
		return utx;
	}
}
