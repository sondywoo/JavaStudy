package com.designPattern.factoryMethod;

public interface IFactory {
	public IProduct getProduct();
	public IProduct getProduct(String productKey);
}
