package com.java.thread.threadsafe;

import java.util.Vector;

/**
 * 线程安全程度 从强到弱 分为：
 *      不可变 > 绝对线程安全 > 相对线程安全 > 线程兼容 > 线程对立
 *
 *      不可变 - 无论是对象的方法实现还是方法的调用者，都不需要再采取任何的线程安全保障措施。
 *          如：基本数据类型 + final 关键字修饰、String类（对象的任何行为不会对其状态产生影响：Long, Double, BigInteger, BigDecimal, 枚举类型）等。
 *      绝对线程安全 - 不管运行时环境如何，调用方都不需要任何额外的同步措施，对对象的所有操作都是线程安全的。Java API中绝大多数都不属于这种情况。
 *      相对线程安全 - 对这个对象的所有单独的操作是线程安全的。
 *          如：Vector, HashTable, Collections 的 synchronizedCollection() 方法包装的集合 等。
 *      线程兼容 - 对象本身并不是线程安全的，但可以通过在调用方采用同步措施保障在并发环境中可安全地使用。
 *      线程独立 - 无论调用方是否采取了同步措施，都无法保证在并发环境中可安全地使用的代码。
 *          如：Thread 的 suspend() 和 resume() 方法，。
 *
 * 绝大部分Java的线程安全类，像 Vector，都属于"相对线程安全"。
 */
public class TestThreadSafe {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] a){
        while(true){
            for(int i=0; i<10; i++){
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable(){
                public void run() {
                    // 虽然 java.util.Vector 是线程安全的容器，所有方法都被 synchronized 修饰的。
                    // 但如果在调用方如果不做额外的同步措施的话，这段代码仍然是不安全的。
                    // 偶尔也会抛 java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 12
                    // 所以这里也要加 synchronized 控制
                    synchronized (vector) { // 操作于共享对象
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable(){
                public void run() {
                    // 虽然 java.util.Vector 是线程安全的容器，所有方法都被 synchronized 修饰的。
                    // 但如果在调用方如果不做额外的同步措施的话，这段代码仍然是不安全的。
                    // 偶尔也会抛 java.lang.ArrayIndexOutOfBoundsException: Array index out of range: 12
                    // 所以这里也要加 synchronized 控制
                    synchronized (vector) { // 操作于共享对象
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println(vector.get(i) + " - " + vector.size());
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            // 防止同时产生过多的线程，否则会导致 OS 假死。
            while(Thread.activeCount() > 40);
        }
    }
}
