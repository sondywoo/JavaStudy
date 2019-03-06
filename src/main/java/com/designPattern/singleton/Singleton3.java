package com.designPattern.singleton;

/**
 * << 最常用 >>
 * Singleton - 3. 静态内部类实现单例
 *  - 高效率，又不需要加同步块，又可以延迟加载
 * 
 * 
 * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()方法第一次调用的时候才会被加载
 * （实现了lazy），而且其加载过程是线程安全的。内部类加载的时候实例化一次instance。
 * 
 * @author Sondy Woo
 */
public class Singleton3 {
	private Singleton3(){}	//私有化构造方法， 这样别的类就没法调到。
	 
	private static class SingletonHolder {
		private final static Singleton3 INSTANCE = new Singleton3();
	}
	
	public static Singleton3 getInstance(){
		return SingletonHolder.INSTANCE;
	}
}
