package com.java.reflection;

import java.lang.reflect.Method;


// 框架类
public class TestReflection {
	public static void main(String[] args) throws Exception{
		int data = 1;
		String name="ClassA";//args[0];
		String method="runA";//args[1];
		if(data == 1){
			Class c=Class.forName(name);
			Object o=c.newInstance();
			  
			Method m=c.getDeclaredMethod(method, new Class[]{});
			m.invoke(o, new Object[]{});
		}
//		if(data == 0){
//			ClassB b=new ClassB();
//			b.runB();
//		}
	}
}
// 	java 包名.Main类名  包名.业务类名  业务方法名--一定要"包名.业务类名"!
//	ex.	java TestReflection ClassA runA
//		java TestReflection ClassB runB



// 业务类B
class ClassB {
	public void runB(){
		System.out.println("in ClassB.");
	}
}