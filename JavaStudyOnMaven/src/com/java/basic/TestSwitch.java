package com.java.basic;

public class TestSwitch {
	
	void hello(){
		char c = 'a';
		
		System.out.println((c+1));			// 打印ASCII码
		System.out.println((char)(c+1));	// 打印字符
		System.out.println(((char)c+1));	// 打印ASCII码
		
		System.out.println("---------------------------");		
		
		switch((c+1)){				// 变量类型只能是 char, byte, short, & int
			case 98:	// 重定义了
				System.out.println("98");	// 打印
				break;
			case 'a':
				System.out.println("aa");
				break;
//			case 'b':
//				System.out.println("bb");	// 打印
//				break;
			case 'c':
				System.out.println("cc");
				break;
			default:
				System.out.println("default: " + (c+1));
		}
		
		switch((char)(c+1)){
			case 98:	// 重定义了
				System.out.println("98");	// 打印
				break;
			case 'a':
				System.out.println("aa");
				break;
//			case 'b':	// 重定义了
//				System.out.println("bb");
//				break;
			case 'c':
				System.out.println("cc");
				break;
			default:
				System.out.println("default: " + (char)(c+1));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSwitch t = new TestSwitch();
		t.hello();
	}
}
