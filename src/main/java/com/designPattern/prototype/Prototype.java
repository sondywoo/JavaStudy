package com.designPattern.prototype;

/**
 * Prototype
 *  - 声明一个克隆自身的接口
 *  - 方法二：implements Cloneable 接口，再Override 方法clone()，这种克隆方法会 x.clone().equals(x) = TRUE
 *  - 本例子中的x.myClone().equals(x) = FALSE
 * 
 * @author Sondy Woo
 */
public interface Prototype {
	public Prototype myClone();	//	不能用clone()，该方法名Object中已经有了
}
