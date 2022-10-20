package com.java.basic;

import java.util.LinkedList;

public class TestFinal implements Comparable<TestFinal>{
    public static void main(String[] args){
        final Integer i;
        i = new Integer(10); // it works here in first initialization!!
        //i = new Integer(11); // it failed here!!
    }


    public int compareTo(TestFinal o) {
        return 0;
    }
}
