package com.designPattern.singleton;

/**
 * 设计模式 -- Singleton 单例模式
 * 
 *  - 保证一个类有且仅有一个实例，并提供一个访问它的全局访问点
 * 
 * @author Sondy Woo
 */
public class TestSingleton {

	/**
	 * Client 客户端
	 * Client里调用的对象只有1个：单例 Singleton 的getInstance（）方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Singleton1 singleton11 = Singleton1.getInstance();
		Singleton1 singleton12 = Singleton1.getInstance();
		System.out.println(singleton11.equals(singleton12) + " - singleton11.equals(singleton12)");
		System.out.println((singleton11 == singleton12) + " - singleton11 == singleton12");

		Singleton2 singleton21 = Singleton2.getInstance2();
		Singleton2 singleton22 = Singleton2.getInstance2();
		System.out.println(singleton21.equals(singleton22) + " - singleton21.equals(singleton22)");
		System.out.println((singleton21 == singleton22) + " - singleton21 == singleton22");

		Singleton3 singleton31 = Singleton3.getInstance();
		Singleton3 singleton32 = Singleton3.getInstance();
		System.out.println(singleton31.equals(singleton32) + " - singleton31.equals(singleton32)");
		System.out.println((singleton31 == singleton32) + " - singleton31 == singleton32");

//		Singleton4 singleton41 = Singleton4.getInstance();
//		Singleton4 singleton42 = Singleton4.getInstance();
//		System.out.println(singleton41.getClass().getName());
//		System.out.println(singleton41.equals(singleton42) + " - singleton41.equals(singleton42)");
//		System.out.println((singleton41 == singleton42) + " - singleton41 == singleton42");
//
//		Singleton4.registerSingleton("childSingleton45", new ChildSingleton44());
	}
}
