package com.designPattern.composite;

/**
 * Component
 *  - 为组合中的对象声明接口
 *  - 在适当的情况下，实现所有类共有接口的缺省行为
 *  - 声明一个接口用于访问和管理 Component 的子组件
 * 
 * @author sondy_wu
 */
public class Component {
	public void operation(){
		
	}
	public void add(Component component){
		
	}
	public void remove(Component component){
		
	}
	public Component getChild(int index){
		return null;
	}
}
