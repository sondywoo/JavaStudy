package com.java.basic.constructor;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Person p1 = new Person();
		/**
		 * 非法的构造方法 - 
		 * 		* 系统会默认提供什么都不做的构造方法 public Person(){}
		 * 		* 但是如果自己定义了构造方法，无论有没有带参的，此时系统就不会提供默认构造方法了。
		 */
		
		Person p2 = new Person("Sondy Woo", "M", 20);
		
		System.out.println("Person: " + p2.getName());
		
		
		/* 测试克隆 */
		Person pClone;
		pClone = p2;	// 整体赋值
		
		System.out.println("Person Cloned: " + pClone.getName());
	}
}