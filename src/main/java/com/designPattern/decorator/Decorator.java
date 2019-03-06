package com.designPattern.decorator;

/**
 * Decorator
 *  - 维护一个指向 Component 对象的指针，用于保存要动态添加新职责的对象(ConcreteComponent)
 *  - 定义一个与 Component 接口一致的接口
 * 
 * @author Sondy Woo
 */
public class Decorator implements Component {
	private Component component;
	public Decorator(Component component){
		this.component = component;
	}
	
	public void operation() {
		System.out.println("Decorator -> Operation() -- " + this);
		component.operation();
	}
}
