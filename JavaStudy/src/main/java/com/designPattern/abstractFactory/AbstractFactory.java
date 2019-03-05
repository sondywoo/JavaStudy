package com.designPattern.abstractFactory;

/**
 * 虚拟工厂
 */
public interface AbstractFactory {
	public AbstractProductA createProductA();
	public AbstractProductB createProductB();
}
