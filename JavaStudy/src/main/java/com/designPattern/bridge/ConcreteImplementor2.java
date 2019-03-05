package com.designPattern.bridge;

/**
 * ConcreteImplementor
 *  - 实现 Implementor 接口并定义它的具体实现
 * 
 * @author Sondy Woo
 */
public class ConcreteImplementor2 implements Implementor{
	public boolean operationImpl() {
		System.out.println("ConcreteImplementor2 -- operationImpl()");
		return false;
	}
}
