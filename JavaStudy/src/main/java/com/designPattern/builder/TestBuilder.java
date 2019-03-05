package com.designPattern.builder;

/**
 * 设计模式 -- Builder 生成器模式
 *  - 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示
 *  - Builder & Abstract Factory 的区别：
 *  - 	Builder 模式着重于一步步构造一个复杂对象； Abstract Factory 模式着重于多个系列的产品对象(简单的/复杂的)
 *  - 	Builder 在最后一步返回产品； Abstract Factory 是立即返回产品
 * 
 * @author Sondy Woo
 */
public class TestBuilder {

	/**
	 * Client 客户端，负责调用导向器Director制造相应的产品
	 * Client里调用的对象只有3个：导向器 Director, 生成器 Builder, 相应的实体产品
	 * 
	 *  - 产品没有抽象父类 -- 因为具体生成器生成的产品，他们的表示相差非常大以至于给不同的产品已公共父类没有多大意思
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Director director = new Director();
		
		//	生成第 1 种 Product - ConcreteProduct1
		ConcreteBuilder1 builder = new ConcreteBuilder1();	//第一种实现方法
		director.createProduct(builder);
		ConcreteProduct1 product1 = builder.getResult();						//调用ConcreteBuilder1 -> getResult()
		product1.work();

		//	生成第 2 种 Product - ConcreteProduct2
		Builder builder2 = new ConcreteBuilder2();			//第二种实现方法
		director.createProduct(builder2);
		ConcreteProduct2 product2 = (ConcreteProduct2) builder2.getResult();	//调用ConcreteBuilder2 -> getResult()
		product2.run();
	}
}