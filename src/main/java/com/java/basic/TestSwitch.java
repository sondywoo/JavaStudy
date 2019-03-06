package com.java.basic;

public class TestSwitch {
	
	void hello(){
		char c = 'a';
		
		System.out.println((c+1));			// ��ӡASCII��
		System.out.println((char)(c+1));	// ��ӡ�ַ�
		System.out.println(((char)c+1));	// ��ӡASCII��
		
		System.out.println("---------------------------");		
		
		switch((c+1)){				// ��������ֻ���� char, byte, short, & int
			case 98:	// �ض�����
				System.out.println("98");	// ��ӡ
				break;
			case 'a':
				System.out.println("aa");
				break;
//			case 'b':
//				System.out.println("bb");	// ��ӡ
//				break;
			case 'c':
				System.out.println("cc");
				break;
			default:
				System.out.println("default: " + (c+1));
		}
		
		switch((char)(c+1)){
			case 98:	// �ض�����
				System.out.println("98");	// ��ӡ
				break;
			case 'a':
				System.out.println("aa");
				break;
//			case 'b':	// �ض�����
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
