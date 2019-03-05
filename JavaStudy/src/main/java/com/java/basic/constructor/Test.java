package com.java.basic.constructor;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Person p1 = new Person();
		/**
		 * �Ƿ��Ĺ��췽�� - 
		 * 		* ϵͳ��Ĭ���ṩʲô�������Ĺ��췽�� public Person(){}
		 * 		* ��������Լ������˹��췽����������û�д��εģ���ʱϵͳ�Ͳ����ṩĬ�Ϲ��췽���ˡ�
		 */
		
		Person p2 = new Person("Sondy Woo", "M", 20);
		
		System.out.println("Person: " + p2.getName());
		
		
		/* ���Կ�¡ */
		Person pClone;
		pClone = p2;	// ���帳ֵ
		
		System.out.println("Person Cloned: " + pClone.getName());
	}
}