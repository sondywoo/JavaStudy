package com.designPattern.singleton;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Singleton - 4. 单件注册表(registry of singleton)
 *  - 所有的 Singleton 子类必须继承 Singleton3
 *  - 允许多种 Singleton 类，并且可灵活选择要初始化哪个Singleton实例
 *  - 所有可能用到的Singleton子类必须在注册表("resource/singletonConfig.properties")中注册先
 * 
 * @author Sondy Woo
 */
public class Singleton4 {
	private static Singleton4 instance = null;
	private static Properties singletonConfig = new Properties();
//	private Singleton3(){}	//私有化构造方法， 这样别的类就没法调到。
	
	static{
		try{
			InputStream is = Singleton4.class.getClassLoader().getResourceAsStream("resource/singletonConfig.properties");
			singletonConfig.load(is);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	protected static Singleton4 lookup(){
		return lookup(null);
	}
	protected static Singleton4 lookup(String keyName){
		Singleton4 singleton = null;
		try{
			if(keyName == null){
				keyName = singletonConfig.getProperty("singleton");
			}
			System.out.println(keyName);
			String className = singletonConfig.getProperty(keyName);
			System.out.println(className);
			if(className != null){
				Class c = Class.forName(className);
				singleton = (Singleton4) c.newInstance();
			}else{
				singleton = new Singleton4();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return singleton;
	}
	
	public static Singleton4 getInstance(){
		if(instance == null){
			String keyName = singletonConfig.getProperty("singleton");
			instance = lookup(keyName);
		}
		return instance;
	}
	
	//	新创建的 Singleton 子类注册到配置文件中
	public static void registerSingleton(String keyName, Singleton4 singleton){
		if(singletonConfig.containsKey(keyName)){		//	如果配置文件中已经注册了就不再注册
			return;
		}
		OutputStream os = null;
		try{
			String filePath = "E:/Project/Workspace/JavaStudy/src/resource/singletonConfig.properties";
			os = new FileOutputStream(filePath, true);	//	向配置文件中追加新建的 Singleton 子类
			Properties singletonConfigAppended = new Properties();	//	需新建一个空白的 Properties实例
			String className = singleton.getClass().getName();
			String comments = keyName + " = " + className;
			System.out.println("\n\n" + comments);
			
			singletonConfigAppended.setProperty(keyName, className);
//			singletonConfigAppended.store(os, comments);	//	向配置文件中添加注释
			singletonConfigAppended.store(os, null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{os.close();}catch(Exception ex){ex.printStackTrace();}
		}
	}
}
