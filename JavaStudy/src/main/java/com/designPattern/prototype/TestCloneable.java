package com.designPattern.prototype;

import java.io.*;

/**
 * 克隆 - 方法二：使用 implements Cloneable 接口，再Override 方法clone()
 * 
 * @author Sondy Woo
 */
public class TestCloneable {
	public static void main(String[] args) throws Exception {
		Father f=new Father();
		
		User u1=new User("123456",f);
		User u2=(User)u1.clone();
		
		System.out.println("u1.password="+u1.password);
		System.out.println("u2.password="+u2.password);
		u1.password="654321";
		System.out.println("u1.password="+u1.password);
		System.out.println("u2.password="+u2.password);
		System.out.println(u1);
		System.out.println(u2);
		
		System.out.println(u1 == u2);
		System.out.println(u1.equals(u2));
		System.out.println(u1.clone().equals(u1));
		
		System.out.println(u1.f == u2.f);
		System.out.println(u1.f.equals(u2.f));
	}
}
class User implements Cloneable,Serializable{
	String password;
	Father f;
	public User(String password,Father f){
		this.password=password;
		this.f=f;
	}
	public Object clone() throws CloneNotSupportedException {
//		return super.clone();
		ObjectOutputStream out=null;
		ObjectInputStream in=null;
		try {
			ByteArrayOutputStream bo=new ByteArrayOutputStream();
			out = new ObjectOutputStream(bo);
			out.writeObject(this);
			out.flush();
			byte[] bs=bo.toByteArray();
			
			ByteArrayInputStream bi=new ByteArrayInputStream(bs);
			in = new ObjectInputStream(bi);
			Object o=in.readObject();
			
			return o;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		finally{
			try {
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Father implements Serializable{}