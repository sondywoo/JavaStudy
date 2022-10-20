package com.designPattern.proxy;

/**
 * 用一个代理对象来作为另一个对象的代理，对客户来说是透明的。
 * 存在一个抽象主题类 (Dao)
 * 具体主题类 (JdbcDao) 和 代理主题类 (ProxyDao) 都继承（实现）抽象主题
 * 代理主题类中的方法会调用具体主题类中相对应的方法。
 */
public class TestProxy {
	public static void main(String[] args) {
		Dao dao = ProxyDao.getProxy();
		dao.queryDB();
	}
}
