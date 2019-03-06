package com.java.collection.map;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

/*
 * 	Properties 特点：
 * 		1. extends Hashtable implements Map; 不继承Collection
 * 		2. 一般用于操作.properties文件，所以Key，Value一般都是String
 * 		2. (Key, Value)数据对
 * 		3. 无排序，对象类型可不一样, 不过一般都是String；
 * 		4. Value允许重复, 但是Key完全重复的将被覆盖；
 * 		5. Properties是线程安全的；
 */
public class TestProperties {
	public static void main(String[] args) {
		Properties v = new Properties();

		v.put("asdf", "asdf");
		v.put(12, 12);
		v.put(new Date(), 1.23);
		v.put("abc", "abc"+1);	// Key完全相同的将被覆盖掉
		
		v.setProperty("abc", "def");
		System.out.println(v);
		
//		v.remove("abc");
//		System.out.println(v);
	}
}
