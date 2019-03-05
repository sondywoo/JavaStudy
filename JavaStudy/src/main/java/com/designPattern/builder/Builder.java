package com.designPattern.builder;

/**
 * Builder 生成器
 * 	- 为创建一个Product对象的各个部件指定抽象接口
 *  - 之所以定义为类是因为有些继承生成器可能有些构造产品的方法不需要调用，就不必重写
 * 
 * @author Sondy Woo
 */
public class Builder {
	public void buildPartA(){}
	public void buildPartB(){}
	public void buildPartC(){}
	public Object getResult(){return null;}
}
