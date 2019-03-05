package com.designPattern.abstractFactory;

/**
 * 第2系列产品A
 * 
 * @author Sondy Woo
 */
public class ProductA2 implements AbstractProductA{
	public void startWork() {
		System.out.println("-------> A2 -- " + this.getClass().getName());
	}
}
