package com.designPattern.proxy;

public class TestProxy {
	public static void main(String[] args) {
		Dao dao = ProxyDao.getProxy();
		dao.queryDB();
	}
}
