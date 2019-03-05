package com.designPattern.factoryMethod;

/**
 * 实体工厂1 -- 常规方法创建Product实体对象
 * 
 * @author Sondy Woo
 */
public class ConcreteFactory1 implements IFactory{
	public IProduct getProduct() {
		IProduct product = new ConcreteProduct();
		return product;
	}
	public IProduct getProduct(String productKey) {
		return this.getProduct();
	}
}
