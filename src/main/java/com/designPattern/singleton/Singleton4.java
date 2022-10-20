package com.designPattern.singleton;

/**
 * << 最常用 >>
 * Singleton - 4. 静态语句块实现单例
 *  - 高效率? 线程安全？
 * @author Sondy Woo
 */
public class Singleton4 {
	private Singleton4(){}	//私有化构造方法， 这样别的类就没法调到。
	private static Singleton4 INSTANCE;

	static {
		INSTANCE = new Singleton4();
	}
	
	public static Singleton4 getInstance(){
		return INSTANCE;
	}
}
