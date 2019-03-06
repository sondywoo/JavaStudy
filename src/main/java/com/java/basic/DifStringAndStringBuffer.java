package com.java.basic;

/*
 * Difference between String and StringBuffer.
 */
public class DifStringAndStringBuffer {

	public void test(String str){
		str +="love you";
		System.out.println(str);
	}
	public void test(StringBuffer strBuf){
		strBuf.append("love you");
	}
	
	public static void main(String[] args) throws Exception {
		DifStringAndStringBuffer t = new DifStringAndStringBuffer();
		StringBuffer strBuf = new StringBuffer("I ");
		t.test(strBuf);
		System.out.println(strBuf);
		String str = "I ";
		t.test(str);
		System.out.println(str);
	}
}
