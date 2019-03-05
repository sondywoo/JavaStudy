package com.java.collection.list;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/*
 * 	LinkedList 特点：
 * 		1. implements List extends Collection;
 * 		2. 无排序，对象类型可不一样；
 * 		3. 允许重复；
 * 		4. FIFO；
 * 		5. LinkedList 内部都是通过双向链表来存放的，增删（好）查改（差）；
 */
public class TestLinkedList {
	public static void main(String[] args) {
		LinkedList v = new LinkedList();
		
		v.add("asdf");
		v.add(12);
		v.add("abc");
		v.add(3.32);
		v.add(new Date());
		v.add("abc");
		v.add(12);
		v.add(new Date());
		System.out.println(v);
		
		System.out.println("------------------------");
		Iterator it = v.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		v.remove("abc");
		System.out.println(v);
	}
}
