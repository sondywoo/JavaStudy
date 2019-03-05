package com.java.collection.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * 	HashMap 特点：
 * 		1. implements SortedMap extends Map; 不继承Collection
 * 		2. (Key, Value)数据对
 * 		3. 按Key升序排序，Value对象类型可不一样, 但是Key的类型一定要一样；
 * 		4. Value允许重复, 但是Key完全重复的将被覆盖；
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
public class TestTreeMap {
	public static void main(String[] args) {
		Map v = new TreeMap();

		v.put("he", "abc");
		v.put("asdf", "abc");
		v.put("abc", "asdf");
		v.put("asdf", "she");	// Key一样的将覆盖前者
//		v.put(3.32, 3.32);
//		v.put(new Date(), "Birthday");
		System.out.println(v);
		
		v.remove("abc");
		System.out.println(v);
		

		Map<String, String> staff = new TreeMap<String, String>();
		staff.put("0003", "Lanny");
		staff.put("0002", "Michael");
		staff.put("0001", "Sondy Woo");
		System.out.println(staff);
		
		for(Map.Entry<String, String> entry : staff.entrySet()){
			System.out.println("ID=" + entry.getKey() + "\tName=" + entry.getValue());
		}
	}
}
