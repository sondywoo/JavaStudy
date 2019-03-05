package com.designPattern.singleton;

/**
 * Singleton - 1. 饿汉式单例
 *  - 饿汉式比较安全，不需要加synchronized
 *  - 定义一个Instance操作，允许客户访问它的唯一实例
 *  - 可能负责创建它自己的唯一实例，也可能交由第三方来负责创建
 * 
 * @author Sondy Woo
 */
public class Singleton1 {
	private static Singleton1 instance = new Singleton1();
	private Singleton1(){}	//私有化构造方法， 这样别的类就没法调到。
	public static Singleton1 getInstance(){
		return instance;
	}
}
