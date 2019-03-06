package com.java.collection.set;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * 	HashSet 特点：
 * 		1. implements Set extends Collection;
 * 		2. 无序，对象类型可不一样；
 * 		3. 内部按散列表结构存储，无序；
 * 		4. 不允许重复；
 * 		5. 添加新元素的速度比树状稍快一点，但比数组或链表插入到中间恰当位置要快很多。
 * 
 * 	比较 散列表集 HashSet & 树状集 TreeSet：
 * 	HashSet：
 * 		采用 散列表数据结构；
 * 		对里面的元素进行散列；
 * 		无序排列；
 * 		运行速度比较快，如果不需要按照排列顺序来访问元素的话最好选择散列表集
 * 	TreeSet：
 * 		采用 树状数据结构；
 * 		使用元素的全局顺序进行排序，并将其组织成搜索树；
 * 		有序排列；
 * 		运行速度比较慢。
 */
public class TestHashSet {
	public static void main(String[] args){
		Set v = new HashSet();
		
		v.add("asdf");
		v.add(12);
		v.add("abc");
		v.add(3.32);
		v.add(new Date());
		v.add("abc");
		v.add(12);
		v.add(new Date());
		
		System.out.println(v);
		
//		//	非线程安全的collection类 --> 线程安全的方法：
//		//
//		//任何一种非线程安全的collection类都可以通过同步包装器(synchronization wrapper)变成线程安全的：
//		//经过同步包装的collection中的方法都会由一个锁保护起来，从而提供了线程安全的访问。
//		//但是，如果你想迭代这个collection，你必须额外使用一个同步块：
//		Set synchHashSet = Collections.synchronizedSet(new HashSet());
//		...
//		synchronized(synchHashSet) {
//			Iterator i = synchHashSet.iterator(); // Must be in the synchronized block
//			while (i.hasNext()){
//				myMethod(i.next());
//			}
//		}
	}
}
