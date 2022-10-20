package com.java.basic;

public class TestDataType {
    public static void main(String[] args) {
        System.out.println("Testing Boolean ......");
        System.out.println(new Boolean("abc"));  // false
        System.out.println(new Boolean("true")); // true
        System.out.println(new Boolean("tRUe")); // true

        System.out.println("Testing char ......");
        char zhChar = '中';
        char ucChar = '\u0060';
        System.out.println(ucChar + " - " + zhChar);

        System.out.println("Testing Integer ......");
        Integer i=4;
        Integer j=4;
        System.out.println(i==j);       // true
        System.out.println(i.equals(j));// true
        i=128; j=128;
        System.out.println(i==j);       // false
        System.out.println(i.equals(j));// true
        int a=128;
        Integer b=128;
        System.out.println(a==b);       // true

        System.out.println("Testing Short ......");
        Short s1=127,s2=127;
        System.out.println(s1==s2);     // true
        Short s3=128,s4=128;
        System.out.println(s3==s4);     // false
        short s5=128,s6=128;
        System.out.println(s5==s6);     // true

        System.out.println("Testing Byte ......");
        Byte b1=127,b2=127;
        System.out.println(b1==b2);     // true

        System.out.println("Testing Long ......");
        Long l1=127l,l2=127l;
        System.out.println(l1==l2);     // true
        Long l3=128l,l4=128l;
        System.out.println(l3==l4);     // false
        long l5=128l,l6=128l;
        System.out.println(l5==l6);     // true
    }
}
