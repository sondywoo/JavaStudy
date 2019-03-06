package com.designPattern.decorator;

/**
 * ConcreteDecorator
 *  - 定义要向组件添加的职责
 * 
 * @author Sondy Woo
 */
public class ConcreteDecorator2 extends Decorator {
	public ConcreteDecorator2(Component component) {
		super(component);
	}
	
	private void work(){
		System.out.println("ConcreteDecorator2 -> work() -- " + this);
	}

	@Override
	public void operation() {	//在调用目的组件的方法的之前或之后插入一些需要添加的职责
		work();
		super.operation();
		work();
	}
}
