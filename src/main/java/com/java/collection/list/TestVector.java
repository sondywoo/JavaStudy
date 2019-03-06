package com.java.collection.list;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/*
 * 	Vector 特点：
 * 		1. implements List extends Collection;
 * 		2. 无排序，对象类型可不一样；
 * 		3. 允许重复；
 * 		4. FIFO；
 * 		5. ArrayList跟Vector 内部都是通过Object数组来存放的，Vector是线程安全的，其他的跟ArrayList一模一样；
 */
public class TestVector {
	public static void main(String[] args) {
		List v = new Vector();
		
		v.add("asdf");
		v.add(12);
		v.add("abc");
		v.add(3.32);
		v.add(new Date());
		v.add("abc");
		v.add(12);
		v.add(new Date());
		System.out.println(v);
		
		v.remove("abc");
		System.out.println(v);
	}
}
