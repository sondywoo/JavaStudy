package com.designPattern.abstractFactory;

/**
 * 设计模式 -- Abstract Factory 虚拟工厂模式
 * 
 *  - 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类
 *  - 虚拟工厂方法模式通常和工厂方法模式一起使用
 *  - 一个具体的工厂通常是一个 Singleton 单件
 *  
 *  区别：工厂方法 & 抽象工厂
 *  工厂方法侧重于给它订单它就会还给你成品，封装成品生成的过程；
 *  抽象工厂侧重于同样的产品经过不同工厂的制造而变得不同。
 *  Ex:
 *  给电脑公司下订单它就会给你相应配置的电脑（工厂方法）；
 *  将该订单交给HP公司得到的是HP品牌的电脑，交给Dell公司得到的是Dell品牌的电脑（抽象工厂）。
 * 
 *  工厂方法模式：
 *  	一个抽象产品类，可以派生出 N 个具体产品类。   
 *  	一个抽象工厂类，可以派生出 N 个具体工厂类。   
 *  	每个具体工厂类只能创建一个具体产品类的实例。
 *  抽象工厂模式：
 *  	M 个抽象产品类，每个抽象产品类可以派生出 N*M 个具体产品类。   
 *  	一个抽象工厂类，可以派生出 N 个具体工厂类。   
 *  	每个具体工厂类可以创建 M 个具体产品类的实例。   
 *  区别：
 *  	工厂方法模式只有一个抽象产品类，而抽象工厂模式有多个。   
 *  	工厂方法模式的具体工厂类只能创建一个具体产品类的实例，而抽象工厂模式可以创建多个。
 *  
 * @author Sondy Woo
 */
public class TestAbstractFactory {

	/**
	 * Client 客户端，负责调用虚拟工厂制造相应的产品
	 * Client里调用的对象只有3个接口：AbstractFactory, AbstractProductA, AbstractProductB
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//	制造第 1 系列的产品 A 和 B.
		AbstractFactory factory1 = new ConcreteFactory1();		
		AbstractProductA productA1 = factory1.createProductA();
		AbstractProductB productB1 = factory1.createProductB();
		productA1.startWork();
		productB1.startRun();
		
		//	制造第 2 系列的产品 A 和 B.
		AbstractFactory factory2 = new ConcreteFactory2();		
		AbstractProductA productA2 = factory2.createProductA();
		AbstractProductB productB2 = factory2.createProductB();
		productA2.startWork();
		productB2.startRun();
	}
}
