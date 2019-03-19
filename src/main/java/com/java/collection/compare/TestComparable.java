package com.java.collection.compare;

import com.common.util.PrintCollection;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class TestComparable {
    public static void main(String[] a){
        Student s1 = new Student("aaa");
        Student s2 = new Student("ccc");
        Student s3 = new Student("bbb");

        //默认升序，Student 中实现的 compareTo 方法决定
        Set<Student> setS1 = new TreeSet<Student>();
        setS1.add(s1);
        setS1.add(s2);
        setS1.add(s3);
        PrintCollection.print(setS1);
        //System.out.println(((TreeSet<Student>) setS1).comparator().compare(s2,s3)); // 出错，此时没有comparator，空指针异常。

        //改造成降序，通过比较器实现。
        Set<Student> setS2 = new TreeSet<Student>(new MyComparator<Student>());
        setS2.add(s1);
        setS2.add(s2);
        setS2.add(s3);
        PrintCollection.print(setS2);
        System.out.println(((TreeSet<Student>) setS2).comparator().compare(s2,s3));

        //使用比较器不需要 <T> 类实现 Comparable 接口
        StudentNonComparable ss1 = new StudentNonComparable("aaa");
        StudentNonComparable ss2 = new StudentNonComparable("ccc");
        StudentNonComparable ss3 = new StudentNonComparable("bbb");
        Set<StudentNonComparable> setS3 = new TreeSet<StudentNonComparable>(new MyComparator<StudentNonComparable>());
        setS3.add(ss1);
        setS3.add(ss2);
        setS3.add(ss3);
        PrintCollection.print(setS3);
        System.out.println(((TreeSet<StudentNonComparable>) setS3).comparator().compare(ss2,ss3));
    }
}
