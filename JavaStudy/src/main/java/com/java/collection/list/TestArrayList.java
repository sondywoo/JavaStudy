package com.java.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*
 * 	ArrayList 特点：
 * 		1. implements List extends Collection;
 * 		2. 无排序，对象类型可不一样；
 * 		3. 允许重复；
 * 		4. FIFO；
 * 		5. ArrayList跟Vector 内部都是通过Object数组来存放的，ArrayList是非线程安全的，增删（差）查改（好）；
 */
public class TestArrayList {
	public static void main(String[] args) {
		List v = new ArrayList();
		
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

//		//	非线程安全的collection类 --> 线程安全的方法：
//		//
//		//任何一种非线程安全的collection类都可以通过同步包装器(synchronization wrapper)变成线程安全的：
//		//经过同步包装的collection中的方法都会由一个锁保护起来，从而提供了线程安全的访问。
//		//但是，如果你想迭代这个collection，你必须额外使用一个同步块：
//		List synchArrayList = Collections.synchronizedList(new ArrayList());
//		...
//		synchronized(synchArrayList) {
//			Iterator i = synchArrayList.iterator(); // Must be in synchronized block
//			while (i.hasNext()){
//				myMethod(i.next());
//			}
//		}
	}
}
