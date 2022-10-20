package com.designPattern.factoryMethod;

import java.io.InputStream;
import java.util.Properties;

/**
 * 实体工厂2 -- 读取配置文件方法创建Product实体对象
 * 
 * @author Sondy Woo
 */
public class ConcreteFactory2 implements IFactory{
	private static Properties classPath = new Properties();
	static{
		try{
			InputStream is = ConcreteFactory2.class.getClassLoader().getResourceAsStream("resource/classPath.properties");
			classPath.load(is);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public IProduct getProduct() {
		IProduct product = null;
		try{
			product = (IProduct) Class.forName(classPath.getProperty("com.product")).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return product;
		}
	}
	public IProduct getProduct(String productKey) {
		IProduct product = null;
		try{
			product = (IProduct) Class.forName(classPath.getProperty(productKey)).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return product;
		}
	}
}
