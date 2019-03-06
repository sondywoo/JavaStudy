package com.designPattern.bridge;

/**
 * RefinedAbstraction
 *  - 扩充由 Abstraction 定义的接口
 *  - Abstraction的子类 RefinedAbstraction 将 Client 的请求转发给它的 Implementor 对象的对应子类，或调用 Implementor 对象的对应子类来完成 Client 请求
 * 
 * @author Sondy Woo
 */
public class RefinedAbstraction1 extends Abstraction{
	
	public RefinedAbstraction1(Implementor implementor) {
		super(implementor);
		//...
		System.out.println("RefinedAbstraction1 -- RefinedAbstraction1(Implementor implementor)");
	}

	@Override
	public boolean operation() {
		System.out.println("RefinedAbstraction1 -- operation()");
		boolean result = this.implementor.operationImpl();
		return result;
	}
}
