package com.java.basic;

public class TestThis {
	
	int v = 111111111;
	
	void hello(){
		int v = 222222222;
		
		System.out.println(this.v);	// 打印全局变量
		System.out.println(v);		// 打印局部变量
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestThis t = new TestThis();
		t.hello();
	}
}
