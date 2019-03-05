package com.java.basic;

public class TestThis {
	
	int v = 111111111;
	
	void hello(){
		int v = 222222222;
		
		System.out.println(this.v);	// ��ӡȫ�ֱ���
		System.out.println(v);		// ��ӡ�ֲ�����
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestThis t = new TestThis();
		t.hello();
	}
}
