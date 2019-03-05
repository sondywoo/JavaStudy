package com.java.innerClass.memberInner;

import static java.lang.System.out;

/**
 * 内部类1：成员内部类
 * 
 * 		- 内部类就像一个实例成员一样存在于外部类中。
 * 		- 内部类可以访问外部类的所有成员就想访问自己的成员一样没有限制。
 * 		- 内部类中的this指的是内部类的实例对象本身，如果要用外部类的实例对象就可以用类名.this的方式获得。
 * 		- 内部类对象中不能有静态成员，原因很简单，内部类的实例对象是外部类实例对象的一个成员。
 * 
 * 创建内部类：
 * 		- 在外部类的内部，可以用 Inner inner = new Inner(); 方法直接创建
 * 		- 在外部类外部，必须先创建外部类实例，然后再创建内部类实例，
 * 			1. Inner inner = new Outer().new Inner();
 * 			2. Outer outer = new Outer(); Inner inner = outer.new Inner();
 */
public class Outer {
	private String a="outer a";
	public void outerMethod1(){
		out.println("Outer,outerMethod1,START");
		
		String a="outer local a";
		out.println("Outer,outerMethod1,a of Outer Local=" + a);
		out.println("Outer,outerMethod1,a of Outer=" + this.a);
		
		Inner testInner = new Inner();
		
		/*
		 * 外部类里引用内部类的属性
		 */
		out.println("Outer,outerMethod1,a of Inner=" + testInner.a);
		
		/*
		 * 外部类里引用内部类的方法
		 */
		testInner.innerMethod2();
	}
	
	class Inner{
		private String a="inner a";
		public void innerMethod1(){
			out.println("Inner,innerMethod1,START");
			
			String a="inner local a";
			out.println("Inner,innerMethod1,a of Inner Local=" + a);
			out.println("Inner,innerMethod1,a of Inner=" + this.a);
			
			/*
			 * 内部类里引用外部类的属性
			 */
			out.println("Inner,innerMethod1,a of Outer=" + Outer.this.a);
			
			/*
			 * 内部类里引用外部类的方法
			 */
			Outer.this.outerMethod1();
		}
		public void innerMethod2(){
			out.println("Inner,innerMethod2,START");
		}
	}
}
