package com.designPattern.abstractFactory;

/**
 * 第1系列产品B
 * 
 * @author Sondy Woo
 */
public class ProductB1 implements AbstractProductB{
	public void startRun() {
		System.out.println("-------> B1 -- " + this.getClass().getName());
	}
}
