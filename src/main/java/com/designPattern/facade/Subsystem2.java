package com.designPattern.facade;

/**
 * Subsystem classes 2
 *  - 实现子系统的功能--实现某个特定子系统的功能
 *  - 处理由Facade对象指派的任务
 *  - 没有Facade的任何相关信息，即没有Facade对象或指向Facade的指针
 * 
 * @author Sondy Woo
 */
public class Subsystem2 {
	public void method2(){
		System.out.println("Subsystem2 -> method2() -- " + this);
		Subsystem1 subsystem1 = new Subsystem1();
		System.out.println("Before call Subsystem1->method1() in Subsystem2!");
		subsystem1.method1();
		System.out.println("After call Subsystem1->method1() in Subsystem2!");
	}
}
