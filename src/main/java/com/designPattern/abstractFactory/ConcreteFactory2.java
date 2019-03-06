package com.designPattern.abstractFactory;

/**
 * 实体工厂2 -- 用于制造产品2系列
 * 
 * @author Sondy Woo
 */
public class ConcreteFactory2 implements AbstractFactory{

	public AbstractProductA createProductA() {
		AbstractProductA productA = new ProductA2();
		return productA;
	}

	public AbstractProductB createProductB() {
		AbstractProductB productB = new ProductB2();
		return productB;
	}
}
