package com.designPattern.facade;

/**
 * Subsystem classes 3
 *  - 实现子系统的功能--实现某个特定子系统的功能
 *  - 处理由Facade对象指派的任务
 *  - 没有Facade的任何相关信息，即没有Facade对象或指向Facade的指针
 * 
 * @author Sondy Woo
 */
public class Subsystem3 {
	public void method3(Subsystem2 subsystem2){
		System.out.println("Subsystem3 -> method3() -- " + this);
		subsystem2.method2();
	}
}
