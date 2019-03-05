package com.java.innerClass.staticInner;

public class TestInnerClass {
	public static void main(String[] args) throws InterruptedException {
		Outer o = new Outer();
		o.outerMethod1();
		System.out.println("------------------");

		/*
		 * 外部类里引用内部类的属性
		 */
		Outer.Inner i = new Outer.Inner();
//		Outer.Inner i = o.new Inner();//出错，只能通过类名初始化
		i.innerMethod1();
		System.out.println("------------------");
		i.innerMethod2();
		
		i=null;
//		System.gc();
		for(int j=1;j<101;j++){
			System.out.println("Sleep " + j + "s");
			Thread.sleep(1000);
		}
		System.out.println("Wake up!");
	}
}
