package com.designPattern.prototype;

/**
 * 设计模式 -- Prototype 原型模式
 * 
 *  - 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象
 *  - 有时候跟 Abstract Factory 模式一起使用，Abstract Factory 可以存储一个被克隆的原型的集合，并返回产品对象
 * 
 * @author Sondy Woo
 */
public class TestPrototype {

	/**
	 * Client 客户端，让一个原型克隆自身从而创建一个新的对象
	 * Client里调用的对象只有2个：原型 Prototype, 相应的实体原型
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Prototype prototype = new ConcretePrototype1();
		ConcretePrototype1 product11 = (ConcretePrototype1) prototype.myClone();
		product11.work();
		System.out.println(product11);
		ConcretePrototype1 product12 = (ConcretePrototype1) prototype.myClone();
		product12.work();
		System.out.println(product12);
		
		System.out.println(product12 == product11);							//	FALSE
		System.out.println(product12.getClass() == product11.getClass());	//	TRUE
		System.out.println(product12.equals(product11));					//	FALSE
		System.out.println(product11.myClone().equals(product11));			//	FALSE
		
		prototype = new ConcretePrototype2();
		ConcretePrototype2 product2 = (ConcretePrototype2) prototype.myClone();
		product2.run();
		System.out.println(product2);
	}
}
