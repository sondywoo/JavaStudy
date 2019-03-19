package com.java.collection.compare;

/**
 * 本身不支持排序的基类
 */
public class StudentNonComparable{
    private String name;
    public String getName() {
        return name;
    }
    StudentNonComparable(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "StudentNonComparable{name='" + name + "'}";
    }
}
