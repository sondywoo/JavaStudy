package com.designPattern.adapter;

/**
 * Adaptee 被适配物 
 *  - 定义一个已经存在的接口，这个接口需要适配
 * 
 * @author sondy_wu
 */
public class Adaptee {
	public void specificRequest(){
		System.out.println("-------> specificRequest() -- " + this.getClass().getName());
	}
}
