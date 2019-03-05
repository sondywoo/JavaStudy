package com.designPattern.builder;

/**
 *  - 表示具体的被构造的复杂对象
 *  - ConcreteBuilder2 创建该产品的内部表示并定义它的装配过程
 *  - 包含定义组成部件的类，包括将这些部件装配成最终产品的接口
 * 
 * @author Sondy Woo
 */
public class ConcreteProduct2 {
	public void run() {
		System.out.println("--------------> p2.run() -- " + this.getClass().getName());
	}
}