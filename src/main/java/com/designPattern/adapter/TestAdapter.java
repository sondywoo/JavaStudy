package com.designPattern.adapter;

/**
 * 设计模式 -- Adapter 适配器模式
 * 
 *  - 别名： Wrapper 包装器
 *  - 将一个类的接口转换成客户希望的另外一个接口
 *  - 该模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * 
 * @author Sondy Woo
 */
public class TestAdapter {
	/**
	 * Client 客户端
	 *  - 负责调用 Target 对象的方法，Target 的被调方法再适配调用被适配对象 Adaptee 的对应方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//1. 测试适配器1 - 类适配器
		Target target1 = new Adapter1();
		target1.request();

		//2. 测试适配器2 - 对象适配器
		Target target2 = new Adapter2();
		target2.request();

		Adaptee adaptee = new Adaptee();
		Target target3 = new Adapter2(adaptee);
		target3.request();
	}
}
