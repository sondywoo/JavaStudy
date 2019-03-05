package com.designPattern.factoryMethod;

public class ConcreteProduct implements IProduct{
	public void showProperty() {
		System.out.println("--------------> p1 -- " + this.getClass().getName());
	}
}
