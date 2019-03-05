package com.sondy.test;

class Grandpa {
    static {
        System.out.println("爷爷在静态代码块");
    }

    public Grandpa() {
        System.out.println("我是爷爷~");
    }
}
class Father extends Grandpa {
    static {
        System.out.println("爸爸在静态代码块");
    }

    public static int factor = 25;
    public static final int ff = 20;

    public Father() {
        System.out.println("我是爸爸~");
    }
}
class Son extends Father {
    static {
        System.out.println("儿子在静态代码块");
    }

    public Son() {
        System.out.println("我是儿子~");
    }
}
public class Demo {
    public static void main(String[] args) {
        System.out.println("爸爸的岁数2:" + Son.ff);  //入口
        System.out.println("爸爸的岁数:" + Son.factor);  //入口

//        爸爸的岁数2:20
//        爷爷在静态代码块
//        爸爸在静态代码块
//        爸爸的岁数:25
    }
}
