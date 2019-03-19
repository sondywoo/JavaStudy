package com.java.basic;

import com.java.basic.constructor.Test;

class SubTestStatic{
    static int i=10;
    SubTestStatic(){
        System.out.println("calling new SubTestStatic()");
    }
    static {    // 静态语句块，JVM首次加载该类的时候调用，有且只有一次调用。
        System.out.println("SubTestStatic >> static codes, i=" + i++);
    }
}

class SonTestStatic extends TestStatic{
//    public void mStatic(){  // 方法重写时，若父类方法是static的，子类该方法也必须是static
//        System.out.println("in SonTestStatic.mStatic:");
//    }
//    public static void mPlusOne(){  // 方法重写时，若父类方法是static的，子类该方法也必须是static
//        System.out.println("in SonTestStatic.mPlusOne:");
//    }
}

public class TestStatic {
    private static int iStatic = 0;
    private int i = 0;
    public static void mStatic(){
        System.out.println("in mStatic:");
        //System.out.println(i); //非法，static方法不能直接访问non-static属性
        System.out.println(iStatic);
    }
    public void mPlusOne(){
        System.out.println("in mPlusOne:");
        System.out.println(++i);
        System.out.println(++iStatic); //所有对象共享，一个更改，全部受影响
    }

    public static void main(String[] args){
        TestStatic ts1 = new TestStatic();
        TestStatic ts2 = new TestStatic();
        ts1.mPlusOne();
        TestStatic.mStatic();
        ts2.mPlusOne();

        System.out.println("calling SubTestStatic...");
        System.out.println("in main, i=" + SubTestStatic.i);
        System.out.println("in main, i=" + SubTestStatic.i);
        new SubTestStatic();
        // output:
        //        in mPlusOne:
        //        1
        //        1
        //        in mStatic:
        //        1
        //        in mPlusOne:
        //        1
        //        2
        //        calling SubTestStatic...
        //        SubTestStatic >> static codes, i=10
        //        in main, i=11
        //        in main, i=11
        //        calling new SubTestStatic()
    }
}
