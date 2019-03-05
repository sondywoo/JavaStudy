package com.java.exception;

public class TestExceptionInherit {
	public static void main(String[] args){
		Exception e1 = new ExceptionA();
		ExceptionA e2 = new ExceptionB();
		
		try{
			throw e1;
		}catch(ExceptionB eb){	//一定要小的Exception在前大的在后，即2个Catch不能颠倒位置。
			eb.printMessage();
		}catch(ExceptionA ea){
			ea.printMessage();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

class ExceptionA extends Exception{
	public void printMessage(){
		System.out.println("A.");
	}
}

class ExceptionB extends ExceptionA{
	public void printMessage(){
		System.out.println("B.");
	}
}