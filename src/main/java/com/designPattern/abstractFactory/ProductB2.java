package com.designPattern.abstractFactory;

/**
 * 第2系列产品B
 * 
 * @author Sondy Woo
 */
public class ProductB2 implements AbstractProductB{
	public void startRun() {
		System.out.println("-------> B2 -- " + this.getClass().getName());
	}
}
