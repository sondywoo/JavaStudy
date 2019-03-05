package com.designPattern.bridge;
/**
 * Implementor
 *  - 定义实现类的接口
 *  - 区别：	1. 该接口不一定要与 Abstraction 接口完全一致，事实上 Implementor 与 Abstraction 可以完全不同
 * 			2. 一般来讲，Implementor 接口仅提供基本操作；Abstraction 则定义了基于这些基本操作的较高层次的操作
 * 
 * @author Sondy Woo
 */
public interface Implementor {
	public boolean operationImpl();
}
