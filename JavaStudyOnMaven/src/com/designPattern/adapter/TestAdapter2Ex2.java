package com.designPattern.adapter;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;

public class TestAdapter2Ex2 {
	public static void main(String[] args) {
		/*
		ArrayList al=new ArrayList();
		al.add("ABC");
		al.add("DEF");
		al.add("123");
		print(al.iterator());
		*/
		Properties ps=new Properties();
		ps.setProperty("P1","aaa");
		ps.setProperty("P2","bbb");
		ps.setProperty("P3","ccc");
		
		Enumeration e=ps.propertyNames();
		Adapter a=new Adapter(e);
		print(a);
	}
	public static void print(Iterator it){
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
class Adapter implements Iterator{
	Enumeration e;
	public Adapter(Enumeration e) {
		this.e = e;
	}
	public boolean hasNext() {
		return e.hasMoreElements();
	}
	public Object next() {
		return e.nextElement();
	}
	public void remove() {
	}
}