package com.arithmetic.grayCode;

public class TestGrayCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for(int i = 0; i < 16; i++){
//			System.out.println(i +"\t"+ Gray.toBinaryString(i) +"\t\t"+ Gray.toBinaryString(Gray.grayCoding(i)) +"\t\t"+ Gray.grayUncoding(Gray.grayCoding(i)));
//		}
		
		System.out.println(Gray.getGrayBinaryStrings(22));
		
//		int a = 12;
//		System.out.println(a);	// 1100
//		
//		// 编码
//		int b = a >>> 1;
//		int c = a ^ b;
//		System.out.println(c);	// 1010
//		
//		// 解码
//		int d = 0;
//		int e = 0;
//		for(int i=32; i>0; i--){
//			d = (c >> (i-1)) & 1;
//			e = (e << 1) | ((e & 1) ^ d);
//		}
//		System.out.println(e);
//		
//		StringBuffer str = new StringBuffer("abc");
//		System.out.println(str.append("def").append(e).reverse());
//		
//		System.out.println("\"" + Gray.toBinaryString(e) + "\"");
//		Gray.printBinaryStr(e);
	}
}
