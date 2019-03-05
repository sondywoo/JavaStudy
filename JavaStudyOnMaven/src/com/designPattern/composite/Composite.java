package com.designPattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite
 *  - 定义有子节点的那些节点的行为
 *  - 存储子部件/子节点
 *  - 在 Component 接口中实现与子部件相关的操作
 * 
 * @author sondy_wu
 */
public class Composite extends Component{
	private int level = 1;
	private List<Component> children = null;
	public Composite(){
		level = 1;
		children = new ArrayList<Component>();
	}
	
	@Override
	public void operation() {
//		System.out.println("\tBEFORE Composite -> Operation()" + this);
		System.out.println("Composite -> Operation() -- " + this);
		for(int i = 0; i < children.size(); i++){
			for(int j = 0; j < this.getLevel(); j++){
				System.out.print("\t");
			}
			children.get(i).operation();
		}
//		System.out.println("\tAFTER Composite -> Operation()" + this);
	}
	@Override
	public void add(Component component) {
		if(component instanceof Composite){
			((Composite)component).setLevel(this.getLevel() + 1);
		}
		children.add(component);
	}
	@Override
	public void remove(Component component) {
		children.remove(component);
	}
	@Override
	public Component getChild(int index) {
		Component child = children.get(index);
		return child;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
