package com.designPattern.factoryMethod;

/**
 * 设计模式 -- Factory Method 工厂方法模式
 * 
 *  - 定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method 是一个类的实例化延迟到其子类
 *  - 工厂方法模式通常和虚拟工厂模式一起使用
 *  
 *  区别：工厂方法 & 抽象工厂
 *  工厂方法侧重于给它订单它就会还给你成品，封装成品生成的过程；
 *  抽象工厂侧重于同样的产品经过不同工厂的制造而变得不同。
 *  Ex:
 *  给电脑公司下订单它就会给你相应配置的电脑（工厂方法）；
 *  将该订单交给HP公司得到的是HP品牌的电脑，交给Dell公司得到的是Dell品牌的电脑（抽象工厂）。
 *  
 *  工厂方法模式：
 *  	一个抽象产品类，可以派生出多个具体产品类。   
 *  	一个抽象工厂类，可以派生出多个具体工厂类。   
 *  	每个具体工厂类只能创建一个具体产品类的实例。
 *  抽象工厂模式：
 *  	多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。   
 *  	一个抽象工厂类，可以派生出多个具体工厂类。   
 *  	每个具体工厂类可以创建多个具体产品类的实例。   
 *  区别：
 *  	工厂方法模式只有一个抽象产品类，而抽象工厂模式有多个。   
 *  	工厂方法模式的具体工厂类只能创建一个具体产品类的实例，而抽象工厂模式可以创建多个。
 * 
 * @author Sondy Woo
 */
public class TestFactoryMethod {

	/**
	 * Client 客户端，负责调用工厂制造相应的产品
	 * Client里调用的对象只有2个接口：IFactory, IProduct
	 * 至于产品究竟是怎么生产出来的，是调用哪个具体产品实现类的，Client端都不关心
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//	实体工厂1 通过常规方法创建产品
		IFactory factory = new ConcreteFactory1();
		IProduct product = factory.getProduct();				//	制造第1种产品
		product.showProperty();

		//	实体工厂2 通过读取配置文件方法创建产品
		//	好处 -- 可以通过修改配置文件达到更改制造产品的品种
		IFactory factory2 = new ConcreteFactory2();
		IProduct product2 = factory2.getProduct();
		product2.showProperty();
		IProduct product3 = factory2.getProduct("com.product");	//	读取配置文件制造第2种产品
		product3.showProperty();
	}
}
