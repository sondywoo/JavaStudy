package com.designPattern.bridge;

/**
 * 设计模式 -- Bridge 桥接模式
 * 
 *  - 别名： Handle/Body
 *  - Abstraction 与 Implementor 之间的关系称为“桥接”，因为它在抽象类与它的实现之间起到了桥梁作用，使它们可以独立地变化
 *  - 继承机制使得客户业务代码与具体实现相关，每当客户创建一业务操作，必须要实例化一个具体的类，这个类有特定的实现部分，即更换了业务就得更改所有的具体实现
 *  - Bridge 模式解决了以上问题，将业务抽象 Abstraction 与它的实现部分 Implementor 分别放在独立的类层次结构中。
 *  - 其中一个类层次结构针对业务接口(Abstraction，RefinedAbstraction)；另一个独立的类层次结构针对实现方式相关的实现部分(Implementor, ConcreteImplementor)
 *  - 对 Abstraction 子类的所有操作都是用 Implementor 接口中的抽象操作实现的
 *  - 假设有N个业务，有M种实现方式/实现平台： 用继承机制得创建 N×M 个实现类；用桥接模式只要创建 N+M 个实现类(N个RefinedAbstraction，M个ConcreteImplementor)
 *  
 *  相关：
 *  	Abstract Factory 模式可以用来创建和配置一个特定的Bridge模式
 *  	区别 Adapter & Bridge：
 *  		Adapter 模式 - 用来帮助无关的类协同工作，它通常在系统设计完成后才会被使用
 *  		Bridge 模式 - 是在系统开始时就被使用，它使得抽象接口和实现部分可以独立进行改变
 * 
 * @author Sondy Woo
 */
public class TestBridge {
	/**
	 * Client 客户端
	 *  - 调用 Abstraction 的实现类 RefinedAbstraction 的业务方法
	 *  - RefinedAbstraction 再通过调用 Implementor 的实现 ConcreteImplementor 的方法来实现相应的业务操作，达到业务与实现分离
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Implementor impl = new ConcreteImplementor1();
		Abstraction abstraction = new RefinedAbstraction1(impl);
		boolean result = abstraction.operation();
		System.out.println(result);

		Implementor impl2 = new ConcreteImplementor2();
		abstraction = new RefinedAbstraction1(impl2);
		result = abstraction.operation();
		System.out.println(result);
	}
}
