package com.designPattern.builder;

/**
 * 具体生成器 - 负责生成ConcreteProduct1
 *  - 实现 Builder 的接口以构造和装配该产品的各个部件
 *  - 定义并明确他所创建的表示
 *  - 提供一个检索产品的接口(ex. getResult())
 *  - 每一个具体生成器只负责生成一种具体的Product
 *  - 没增加一种新 Product 就要相应的增加对应的具体生成器
 * 
 * @author Sondy Woo
 */
public class ConcreteBuilder1 extends Builder {
	private ConcreteProduct1 product = null;
	
	public void buildPartA() {		//	对需要构造部件的方法就重写
		product = new ConcreteProduct1();
		System.out.println("create part A -- " + product);
	}
	public void buildPartB() {
		System.out.println("create part B -- " + product);
	}
	public void buildPartC() {
		System.out.println("create part C -- " + product);
	}

	public ConcreteProduct1 getResult() {
		System.out.println("getResult()   -- " + this.getClass().getName());
		return product;
	}
}
