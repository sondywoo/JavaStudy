package com.designPattern.adapter;

/**
 * Adapter 适配器 - 1. 类适配器方法
 *  - 对 Adaptee 接口与 Target 接口进行适配
 *  - 使用多重继承，适配器 Adapter 继承目标接口 Target 和所有被包装接口 Adaptees
 *  - 优点： 使得Adapter可以重定义Adaptee的部分行为
 *  - 缺点： 不能灵活适配Adaptee本身以及它的子类
 * 
 * @author sondy_wu
 */
public class Adapter1 extends Adaptee implements Target {
	public void request() {
		System.out.println("-------> request() -- " + this.getClass().getName());
		this.specificRequest();
	}
}
