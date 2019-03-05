package com.designPattern.proxy;

import com.common.util.LogUtils;

public class JdbcDao implements Dao {
	public void queryDB() {
		LogUtils.print("JdbcDao,queryDB,START");
	}
}
