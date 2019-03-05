package com.designPattern.bridge;

/**
 * Abstraction
 *  - 定义抽象类的接口
 *  - 维护一个指向 Implementor 类型对象的指针
 *  - Abstraction的子类 RefinedAbstraction 将 Client 的请求转发给它的 Implementor 对象的对应子类，或调用 Implementor 对象的对应子类来完成 Client 请求
 * 
 * @author Sondy Woo
 */
public abstract class Abstraction {
	protected Implementor implementor;
	public Abstraction(Implementor implementor){
		this.implementor = implementor;
		System.out.println("Abstraction -- Abstraction(Implementor implementor)");
	}
	public abstract boolean operation();
}
