package com.designPattern.abstractFactory;

/**
 * 第1系列产品A
 * 
 * @author Sondy Woo
 */
public class ProductA1 implements AbstractProductA{
	public void startWork() {
		System.out.println("-------> A1 -- " + this.getClass().getName());
	}
}
