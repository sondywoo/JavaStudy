package com.java.collection.compare;

/**
 * 本身支持排序的基类
 */
public class Student implements Comparable<Student>{
    private String name;
    Student(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student{name='" + name + "'}";
    }
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }
}
