package com.designPattern.builder;

/**
 *  - 表示具体的被构造的复杂对象
 *  - ConcreteBuilder1 创建该产品的内部表示并定义它的装配过程
 *  - 包含定义组成部件的类，包括将这些部件装配成最终产品的接口
 * 
 * @author Sondy Woo
 */
public class ConcreteProduct1 {
	public void work() {
		System.out.println("--------------> p1.work() -- " + this.getClass().getName());
	}
}