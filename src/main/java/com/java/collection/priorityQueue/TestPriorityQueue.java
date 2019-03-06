package com.java.collection.priorityQueue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

/**
 * java.util.PriorityQueue 优先级队列：
 * 
 * 		1. 内部采用 堆数据结构 存储，堆是一个可以进行自我调整的二叉树；
 * 		2. 无序。优先级队列内部存放数据是无序的；
 * 		3. 调用add(E o) & E remove()方法，将会使优先级最小的元素移动到根部；
 * 		4. 优先级队列持有的元素： 可以是实现了Comparable接口的类的对象；也可以在构造器中提供的Comparator对象。
 */
public class TestPriorityQueue {

	public static void main(String[] args) {
		PriorityQueue<GregorianCalendar> pq = new PriorityQueue<GregorianCalendar>();
		pq.add(new GregorianCalendar(1916, Calendar.NOVEMBER, 9));
		pq.add(new GregorianCalendar(1910, Calendar.JUNE, 22));
		pq.add(new GregorianCalendar(1984, Calendar.MARCH, 29));
		pq.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10));
		//	每次Add完一个元素之后优先级最小的元素总是被移动到根部。
		
		/*
		 * 这里的迭代器并不是按照排列顺序来访问各个元素的；
		 * 但移除操作总是针对当前剩余元素中优先级最小的一个来操作。
		 */
		
		System.out.println("Iterating...");
		//	遍历的时候是随机顺序
		for(GregorianCalendar date : pq){
			System.out.println(date.get(Calendar.YEAR));
		}
		
		System.out.println("Iterating...");
		//	遍历的时候是随机顺序
		for(GregorianCalendar date : pq){
			System.out.println(date.get(Calendar.MONTH));
		}
		
		System.out.println("Removing...");
		//	删除的时候是按时间先后顺序
		while(!pq.isEmpty()){
			System.out.println(pq.remove().get(Calendar.YEAR));
		}
	}
}
