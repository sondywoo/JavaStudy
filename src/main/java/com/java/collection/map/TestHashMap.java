package com.java.collection.map;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * 	HashMap 特点：
 * 		1. implements Map; 不继承Collection
 * 		2. (Key, Value)数据对
 * 		3. 无排序，Key & Value对象类型可不一样；
 * 		4. Value允许重复, 但是Key完全重复的将被覆盖；
 * 		5. HashMap是非线程安全的，其他的跟Hashtable一模一样；
 * 
 * 	映射表 - 比较 散列映射表 HashMap & 树状映射表 TreeMap：
 * 	HashMap：
 * 		对Key采用 散列表数据结构；
 * 		对Key进行散列；
 * 		Key无序排列；
 * 		运行速度比较快，如果不需要按照排列顺序来访问Key的话最好选择散列映射表
 * 	TreeMap：
 * 		对Key采用 树状数据结构；
 * 		对Key使用Key的全局顺序进行排序，并将其组织成搜索树；
 * 		Key有序排列；
 * 		运行速度比较慢。
 */
public class TestHashMap {
	public static void main(String[] args) {
		Map v = new HashMap();
		
		v.put("asdf", "asdf");
		v.put(12, 12);
		v.put("abc", "abc");
		v.put(3.32, 3.32);
		v.put(new Date(), new Date());
		v.put("abc", "abc"+1);	// Key完全相同的将被覆盖掉
		v.put(12, 12);
		v.put("12", 12);
		v.put(new Date(), "Birthday");
		System.out.println(v);
		
		v.remove("abc");
		System.out.println(v);

		Map<String, String> staff = new HashMap<String, String>();
		staff.put("0001", "Sondy Woo");
		staff.put("0002", "Michael");
		staff.put("0003", "Lanny");
		System.out.println(staff);
		
		for(Map.Entry<String, String> entry : staff.entrySet()){
			System.out.println("ID=" + entry.getKey() + "\tName=" + entry.getValue());
		}
		
//		//	非线程安全的collection类 --> 线程安全的方法：
//		//
//		//任何一种非线程安全的collection类都可以通过同步包装器(synchronization wrapper)变成线程安全的：
//		//经过同步包装的collection中的方法都会由一个锁保护起来，从而提供了线程安全的访问。
//		//但是，如果你想迭代这个collection，你必须额外使用一个同步块：
//		Map synchHashMap = Collections.synchronizedMap(new HashMap());
//		...
//		Set keys = synchHashMap.keySet();  // Needn't be in synchronized block
//		...
//		synchronized(synchHashMap) {  // Synchronizing on m, not s!
//			Iterator i = keys.iterator(); // Must be in synchronized block
//			while (i.hasNext()){
//				myMethod(i.next());
//			}
//		}
	}
}
