package com.designPattern.singleton;

/**
 * << 第二常用 >>
 * Singleton - 2. 懒汉式单例
 *  - 懒汉式比较高效率，但是需要加synchronized
 *  - 定义一个Instance操作，允许客户访问它的唯一实例
 *  - 可能负责创建它自己的唯一实例，也可能交由第三方来负责创建
 * 
 * @author Sondy Woo
 */
public class Singleton2 {
	private static Singleton2 instance = null;
	private Singleton2(){}	//私有化构造方法， 这样别的类就没法调到。
	
	/**
	 * 并发度低
	 */
	public static synchronized Singleton2 getInstance(){
		//	一定要加synchronized关键字，否则使用getInstance()方法就有可能得到多个Singleton2实例
		//	缺陷： 并发度不高
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}

	/**
	 * 并发度高
	 */
	public static Singleton2 getInstance2(){
		if(instance == null){
			synchronized(Singleton2.class){	
				if(instance == null){
					instance = new Singleton2();
				}
			}
		}
		return instance;
	}
}
