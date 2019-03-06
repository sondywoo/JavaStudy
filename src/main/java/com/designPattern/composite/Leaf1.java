package com.designPattern.composite;

/**
 * Leaf - 叶子节点1
 *  - 在组合中表示叶节点对象，叶子节点没有子节点
 *  - 在组合中定义图元对象的行为
 * 
 * @author sondy_wu
 */
public class Leaf1 extends Component{
	@Override
	public void operation() {
		super.operation();
		System.out.println("Leaf1 -> Operation() -- " + this);
	}
}
