package com.designPattern.decorator;

/**
 * ConcreteComponent
 *  - 定义一个对象，可以给这个对象动态地添加一些职责
 * 
 * @author Sondy Woo
 */
public class ConcreteComponent implements Component {
	public void operation() {
		System.out.println("ConcreteComponent -> Operation() -- " + this);
	}
}
