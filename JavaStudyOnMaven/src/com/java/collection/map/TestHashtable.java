package com.java.collection.map;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/*
 * 	Hashtable 特点：
 * 		1. implements Map; 不继承Collection
 * 		2. (Key, Value)数据对
 * 		3. 无排序，对象类型可不一样；
 * 		4. Value允许重复, 但是Key完全重复的将被覆盖；
 * 		5. Hashtable是线程安全的，其他的跟HashMap一模一样；
 */
public class TestHashtable {
	public static void main(String[] args) {
		Map v = new Hashtable();
		
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
	}
}
