package com.designPattern.proxy;

import com.common.util.LogUtils;

public class ProxyDao implements Dao {
	
	private Dao dao;
	public ProxyDao(Dao dao){
		this.dao = dao;
	}
	
	public static Dao getProxy(){
		Dao dao = new JdbcDao();
		return new ProxyDao(dao);
	}
	
	public void queryDB() {
		LogUtils.print("ProxyDao,queryDB,START");
		dao.queryDB();
	}
}
