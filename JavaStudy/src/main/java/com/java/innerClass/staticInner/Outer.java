package com.java.innerClass.staticInner;

import static java.lang.System.out;

/**
 * 内部类2：静态内部类
 * 
 * 		- 外面类访问内部类的时候不需要 new 外部类；
 * 		- 内部类只能引用外部类的静态属性、静态方法。
 */
public class Outer {
	private String a="outer a";		//静态内部类不能访问外部类的实例属性
	private static String b="outer static b";

	public static void outerMethod2(){
		out.println("Outer,outerMethod2,static method START");
	}
	public void outerMethod1(){		//静态内部类不能访问外部类的实例方法
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
	
	static class Inner{
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			System.out.println("Inner,finalize--------------------------->>");
		}
		
		private String a="inner a";
		private static String b="inner static b";
		public void innerMethod1(){
			out.println("Inner,innerMethod1,START");
			
			String a="inner local a";
			out.println("Inner,innerMethod1,a of Inner Local=" + a);
			out.println("Inner,innerMethod1,b of Inner=" + b);
			out.println("Inner,innerMethod1,a of Inner=" + this.a);
			
			/*
			 * 内部类里引用外部类的属性
			 * 只能引用外部类的静态属性
			 */
			out.println("Inner,innerMethod1,b of Outer=" + Outer.b);
//			out.println("Inner,innerMethod1,a of Outer=" + Outer.this.a);//出错，不能引用外部类的非静态属性
			
			/*
			 * 内部类里引用外部类的方法
			 * 只能引用外部类的静态方法
			 */
			Outer.outerMethod2();
//			Outer.this.outerMethod1();//出错，不能引用外部类的非静态方法
		}
		public void innerMethod2(){
			out.println("Inner,innerMethod2,START");
		}
	}
}
