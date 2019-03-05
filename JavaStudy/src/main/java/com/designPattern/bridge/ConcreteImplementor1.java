package com.designPattern.bridge;

/**
 * ConcreteImplementor
 *  - 实现 Implementor 接口并定义它的具体实现
 * 
 * @author Sondy Woo
 */
public class ConcreteImplementor1 implements Implementor{
	public boolean operationImpl(){
		System.out.println("ConcreteImplementor1 -- operationImpl()");
		return true;
	}
}
