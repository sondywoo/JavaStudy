package com.designPattern.prototype;

/**
 * ConcretePrototype
 *  - 实现一个克隆自身的操作
 * 
 * @author Sondy Woo
 */
public class ConcretePrototype1 implements Prototype{
	private String name;
	public void setName(String name){
		this.name = name;
	}
	
	public ConcretePrototype1(){}
	public ConcretePrototype1(ConcretePrototype1 product){
		name = product.name;
	}
	
	public ConcretePrototype1 myClone() {
		System.out.println("--------------> ConcretePrototype1.myClone()");
		return new ConcretePrototype1(this);
	}
	public void work(){
		System.out.println("--------------> p1.work() -- " + this.getClass().getName());
	}
}