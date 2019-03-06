package com.java.innerClass.anonymousInner;

/**
 * 内部类4：匿名内部类
 * 
 * 		- 匿名内部类可定义在方法形参里、方法体里。
 * 		- 匿名内部类可以是 “某个类的继承子类” 也可以是 “某个接口的实现类” 。
 * 		- 匿名内部类用 new Pet(){...} 的方式把声明类的过程和创建类的实例的过程合二为一。
 * 		- 某个类的继承子类：new <父类名>(){...<覆盖类的方法>}
 * 		- 某个接口的实现类：new <接口名>(){...<实现接口的方法>}
 */
public class Outer {
    public void callInner(Object obj){
    	System.out.println(obj.toString());
    	
        Dog dog = new Dog(){//定义 “某个接口的实现类” 
            public void beFriendly() {
                System.out.println("蹭蹭你^_^");
            }
            public void play() {
                System.out.println("把飞盘叼给你，逼你把飞盘丢出去，然后它再捡回来让你继续扔，连续500次^_^");
            }
        };
        dog.beFriendly();
        dog.play();
    }
    
    public interface Dog {
        public void beFriendly();
        public void play();
    }
    
    public static void main(String[] args){
        Outer outer = new Outer();
        outer.callInner(new Object(){//定义 “某个类的继承子类” 
			@Override
			public String toString() {
				return "Helloworld! This is Sondy";
			}
        });
    }
}
