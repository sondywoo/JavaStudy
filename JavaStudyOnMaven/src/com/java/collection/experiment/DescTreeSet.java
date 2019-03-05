package com.java.collection.experiment;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/*
 * 	把TreeSet默认的ASC排序改成DESC，降序排序
 * 	对于用户自定义的类，该类需要implements Comparable<ObjectName>	!!!
 */
public class DescTreeSet {
	public static void main(String[] args) {
//		Set v = new TreeSet();						// 默认的ASC排序
		Set v = new TreeSet(new MyComparator());	// DESC排序
		
		v.add("asdf");
		v.add("abc");
		v.add("...");
		v.add("abc");
//		v.add(12);
//		v.add(new Date());		
		System.out.println(v);
		
		v.removeAll(v);
		System.out.println(v);
		v.add(12d);
		v.add(3.14);
		v.add(5.5);
//		v.add(12);	// Int型，出错！
		v.add(12d);
		System.out.println(v);
		
		// 测试用户自定义的类
		// 用户自定义类可排序的前提条件 --> 类必须implements Comparable<ObjectName>	!!!
		v.removeAll(v);
		System.out.println(v);
		v.add(new Boy(33l, "Lala"));
		v.add(new Boy(22l, "Sondy"));
		v.add(new Boy(5l, "Sondy"));	// 究竟是哪个类要被覆盖取决于该类的int compareTo(Boy anotherBoy)中比较的条件
		v.add(new Boy(22l, "呵呵"));
		System.out.println(v);
	}
}

class MyComparator implements Comparator{
	public int compare(Object o1, Object o2) {
		return - ((Comparable<Object>)o1).compareTo(o2);	// Nice!!!
//		return - ((Comparable</*-*/Object>)o1).compareTo(o2);	// Nice!!!
	}
}
