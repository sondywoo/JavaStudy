package com.java.innerClass.localInner;

/**
 * 内部类3：局部内部类
 * 
 * 		- 局部内部类的地位和方法内的局部变量的位置类似，因此不能修饰局部变量的修饰符也不能修饰局部内部类，譬如public、private、protected、static、transient等
 * 		- 局部内部类只能在声明的方法内是可见的，因此定义局部内部类之后，想用的话就要在方法内直接实例化，记住这里顺序不能反了，一定是要先声明后使用，否则编译器会说找不到。
 * 		- 局部内部类不能访问定义它的方法内的局部变量，除非这个变量被定义为final 。
 */
public class Outer {
    int x =1;
    public void doSomething(){
        final int y=2;
        
        //局部内部类只能先声明再实例化
//        Inner inner2 = new Inner();
//        inner2.print();
        
        class Inner{
            int x =3;
            void print(){
                int x=4;
                System.out.println(x);
                System.out.println(this.x);			//访问内部类的属性
                System.out.println(Outer.this.x);	//访问外部类的属性
                System.out.println(y);				//访问定义该类的方法的变量，只能是final常量
            }
        }
        Inner inner = new Inner();
        inner.print();
    }
 
    public static void main(String[] args){
        Outer outer = new Outer();
        outer.doSomething();
    }
}
