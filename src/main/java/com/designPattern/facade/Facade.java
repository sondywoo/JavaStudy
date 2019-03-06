package com.designPattern.facade;

/**
 * Facade
 *  - 知道各个子系统类负责处理什么样的请求
 *  - 将客户的请求代理给适当的子系统对象
 * 
 * @author Sondy Woo
 */
public class Facade {
	public void operation(){
		System.out.println("Facade -> operation() -- " + this);
		
		Subsystem2 subsystem2 = new Subsystem2();
		Subsystem3 subsystem3 = new Subsystem3();
		subsystem3.method3(subsystem2);
		
		Subsystem4 subsystem4 = new Subsystem4();
		Subsystem5 subsystem5 = subsystem4.method4();
		subsystem5.method5();
	}
}
