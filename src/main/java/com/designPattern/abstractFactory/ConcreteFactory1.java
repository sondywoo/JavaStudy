package com.designPattern.abstractFactory;

/**
 * 实体工厂1 -- 用于制造产品1系列
 * 
 * @author Sondy Woo
 */
public class ConcreteFactory1 implements AbstractFactory{

	public AbstractProductA createProductA() {
		AbstractProductA productA = new ProductA1();
		return productA;
	}

	public AbstractProductB createProductB() {
		AbstractProductB productB = new ProductB1();
		return productB;
	}
}
