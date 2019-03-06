package com.java.innerClass.memberInner;

public class TestInnerClass {
	public static void main(String[] args) {
		Outer o = new Outer();
		o.outerMethod1();
		System.out.println("------------------");

		/*
		 * 外部类里引用内部类的属性
		 */
		Outer.Inner i = o.new Inner();
		i.innerMethod1();
		System.out.println("------------------");
		i.innerMethod2();
	}
}
