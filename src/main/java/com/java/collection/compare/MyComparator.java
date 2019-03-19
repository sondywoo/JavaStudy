package com.java.collection.compare;

import java.util.Comparator;

/**
 * 定制化比较器
 * @param <T>
 */
public class MyComparator<T> implements Comparator<T> {
    public int compare(T o1, T o2) {
        if(o1 instanceof StudentNonComparable){
            StudentNonComparable s1 = (StudentNonComparable) o1;
            StudentNonComparable s2 = (StudentNonComparable) o2;
            return -s1.getName().compareTo(s2.getName());
        } else {
            return -((Comparable<T>) o1).compareTo(o2);
        }
    }
}
