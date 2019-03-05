package com.java.collection.set;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/*
 * 	TreeSet 特点：
 * 		1. implements SortedSet extends Set;
 * 		2. 升序排序，对象类型要求一样；
 * 		3. 清空后可换存另一种对象类型；
 * 		4. 内部按树状结构存储，有序；
 * 		5. 插入新数据平均比较时间为 log2(n)次；
 * 		6. 不允许重复；
 * 		7. 添加新元素的速度比散列表稍慢一点，但比数组或链表插入到中间恰当位置要快。
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
 * 
 * 	条件：
 * 		1. 插入新元素必须实现了Comparable接口
 */
public class TestTreeSet {
	public static void main(String[] args) {
		Set v = new TreeSet();

		v.add("sfg");
		v.add("egrhnfd");
		v.add("asdf");
		v.add("abcd");
		v.add("abc");
		v.add("abc");
//		v.add(12);
//		v.add(new Date());		
		System.out.println(v);
		
		v.removeAll(v);
		System.out.println(v);
		v.add(12d);
		v.add(3.14);
//		v.add(12);	// Int型，出错！
		v.add(12d);
		v.add(12.00);
		System.out.println(v);
	}
}
