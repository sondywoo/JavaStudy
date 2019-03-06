package com.designPattern.facade;

/**
 * Subsystem classes 4
 *  - 实现子系统的功能--实现某个特定子系统的功能
 *  - 处理由Facade对象指派的任务
 *  - 没有Facade的任何相关信息，即没有Facade对象或指向Facade的指针
 * 
 * @author Sondy Woo
 */
public class Subsystem4 {
	public Subsystem5 method4(){
		System.out.println("Subsystem4 -> method4() -- " + this);
		Subsystem5 subsystem5 = new Subsystem5();
		return subsystem5;
	}
}
