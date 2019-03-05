package com.designPattern.factoryMethod;

/*
 * 另一种产品品种
 */
public class ConcreteProduct2 implements IProduct{
	public void showProperty() {
		System.out.println("--------------> p2 -- " + this.getClass().getName());
	}
}
