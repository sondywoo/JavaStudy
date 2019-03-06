package com.designPattern.prototype;

public class ConcretePrototype2 implements Prototype{
	private String name;
	public void setName(String name){
		this.name = name;
	}
	
	public ConcretePrototype2(){}
	public ConcretePrototype2(ConcretePrototype2 product){
		name = product.name;
	}
	
	public ConcretePrototype2 myClone() {
		System.out.println("--------------> ConcretePrototype2.myClone()");
		return new ConcretePrototype2(this);
	}
	public void run(){
		System.out.println("--------------> p2.run() -- " + this.getClass().getName());
	}
}