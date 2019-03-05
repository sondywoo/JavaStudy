package com.designPattern.decorator;

/**
 * ConcreteDecorator
 *  - 定义要向组件添加的职责
 * 
 * @author Sondy Woo
 */
public class ConcreteDecorator1 extends Decorator {
	public ConcreteDecorator1(Component component) {
		super(component);
	}
	
	private void run(){
		System.out.println("ConcreteDecorator1 -> run() -- " + this);
	}

	@Override
	public void operation() {	//在调用目的组件的方法的之前或之后插入一些需要添加的职责
		run();
		super.operation();
		run();
	}
}
