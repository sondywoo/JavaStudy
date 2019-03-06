package com.designPattern.builder;

/**
 * Director 导向器
 *  - 构造一个使用 Builder 接口的对象
 * 
 * @author Sondy Woo
 *
 */
public class Director {
	public Object createProduct(Builder builder){
		builder.buildPartA();
		
		builder.buildPartB();		//	每个部件需要构造多少次由导向器决定
		builder.buildPartB();
		
		builder.buildPartC();
		
		return builder.getResult();
	}
}
