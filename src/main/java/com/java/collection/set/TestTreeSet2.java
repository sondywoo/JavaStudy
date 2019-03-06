package com.java.collection.set;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 	该程序建立2个MyItem对象的树集：
 * 		第一个树集按部件编号进行排序，使用MyItem对象默认的排列顺序；
 * 		第二个树集按项描述进行排序，使用定制的比较器。
 */
public class TestTreeSet2 {
	public static void main(String[] args) {
		
		//	part 1. it's sorted by "partNumber"
		SortedSet<MyItem> parts = new TreeSet<MyItem>();
		parts.add(new MyItem("Widget", 4562));
		parts.add(new MyItem("Modem", 9912));
		parts.add(new MyItem("Toaster", 1234));
		System.out.println(parts);
		
		//	part 2. it's sorted by "description"
		//若想换一种排列顺序就必须重新定义一个TreeSet对象，而不能直接使用原来的TreeSet对象。
		SortedSet<MyItem> parts2 = new TreeSet<MyItem>(new Comparator<MyItem>(){
				//	定制比较器，只需实现“int compare(T a, T b)”方法
				public int compare(MyItem a, MyItem b){
					String descA = a.getDescription();
					String descB = b.getDescription();
					return descA.compareTo(descB);	//	升序
//					return -descA.compareTo(descB);	//	降序
				}
			});
		parts2.addAll(parts);
		System.out.println(parts2);
	}
}

/**
 * 要进行比较的类元素必须实现了Comparable接口
 */
class MyItem implements Comparable<MyItem>{
	
	private String description;
	private int partNumber;
	
	public MyItem(String description, int partNumber){
		this.description = description;
		this.partNumber = partNumber;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString(){
		return "[description=" + description + ", partNumber=" + partNumber + "]";
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		
		MyItem other = (MyItem) o;
		return description.equals(other.description) && partNumber==other.partNumber;
	}

	@Override
	public int hashCode() {
		return 13*description.hashCode() + 17*partNumber;
	}

	public int compareTo(MyItem o) {
		return (partNumber - o.partNumber);		//	升序
//		return -(partNumber - o.partNumber);	//	降序
	}
}
