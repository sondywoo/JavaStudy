package com.designPattern.adapter;

/**
 * Adapter 适配器 - 2. 对象适配器方法
 *  - 对 Adaptee 接口与 Target 接口进行适配
 *  - 使用对象组合，适配器 Adapter 继承目标接口 Target ，并定义所有被包装接口 Adaptees 属性以便调用 Adaptees的对应方法
 *  - 优点： 能灵活适配Adaptee本身以及它的子类
 *  - 缺点： Adapter不可以重定义Adaptee的部分行为
 * 
 * @author sondy_wu
 */
public class Adapter2 implements Target{
	private Adaptee adaptee = null;
	
	public Adapter2(){
		adaptee = new Adaptee();
	}
	public Adapter2(Adaptee adaptee){
		this.adaptee = adaptee;
	}
	
	public void request() {
		System.out.println("-------> request() -- " + this.getClass().getName());
		adaptee.specificRequest();
	}
}
