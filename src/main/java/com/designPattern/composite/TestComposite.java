package com.designPattern.composite;

/**
 * 设计模式 -- Composite 组合模式
 * 
 *  - 将对象组合成树形结构以表示“部分-整体”的层次结构。使得用户对单个对象和组合对象的使用具有一致性
 *  - 使得用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象
 *  - Composite 模式的关键是一个抽象类 Component，它既可以表示图元(Leaf1, Leaf2等)，又可以表示图元的容器 Composite，
 *  		它声明一些与特定图元对象相关的操作(operation())，也声明了所有的组合对象共享的一些操作(add(),remove()等)，用
 *  		于访问和管理它的子部件。
 * 好处：
 * 		1. 定义了包含单个对象和组合对象的类层次结构。单个对象可以被组合成更复杂的组合对象，而组合对象有可以被组合成更复杂的组
 * 			合对象；
 * 		2. 简化了客户代码，客户可以一致地使用组合结构和单个对象，通常用户不知道(也不关心)处理的是一个叶节点还是一个组合组件；
 * 		3. 更容易增加新类型的组件，新定义的Composite/Leaf子类自动地与已有的结构和客户代码一起工作
 * 
 * @author Sondy Woo
 */
public class TestComposite {
	/**
	 * Client 客户端
	 *  - 通过 Component 接口操纵组合部件的对象
	 *  - 客户端调用对象的行为方法： 
	 *  - 	1. 如果接收者对象是叶子节点(Leaf1, Leaf2等)，则直接处理请求；
	 *  - 	2. 如果接收者对象是 Composite，则将请求转发给它的所有的子部件，在转发请求之前与/或之后可能执行一些辅助的操作
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Component head = new Composite();
		
			Component child1A = new Leaf1();
			Component child1B = new Leaf1();
			Component child1C = new Leaf2();
			Component child1D = new Composite();
			head.add(child1A);
			head.add(child1B);
			head.add(child1D);
			head.add(child1C);
			head.add(child1B);
			
				Component child2A = new Leaf2();
				Component child2B = new Leaf1();
				Component child2C = new Leaf2();
				child1D.add(child2A);
				child1D.add(child2B);
				child1D.add(child2C);
				Component child2D = new Composite();
				child1D.add(child2D);

					Component child3A = new Leaf2();
					Component child3B = new Leaf1();
					child2D.add(child3A);
					child2D.add(child3B);
		
		head.operation();
	}
}
