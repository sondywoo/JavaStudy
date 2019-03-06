package com.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/*
 * 	Print all constructors, methods, and fields of a Class given in the form of "packageName.ClassName".
 */
public class TestReflection2 {
	public static void main(String[] args) {
//		if(args.length == 0){
//			System.out.println("Usage: java TestReflection2 packageName.ClassName");
//			System.exit(-1);
//		}
//		String className = args[0];
		String className = "TestReflection";
		try{
			Class cls = Class.forName(className);
			Class superCls = cls.getSuperclass();
			System.out.print("class " + className);
			if(superCls != null && !superCls.equals(Object.class)){
				System.out.print(" extends " + superCls.getName());
			}
			System.out.print("{\n\n");
			
			printConstructors(cls);
			System.out.println();
			
			printMethods(cls);
			System.out.println();
			
			printFields(cls);
			System.out.println("}");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void printConstructors(Class cls){
		Constructor[] constructors = cls.getDeclaredConstructors();
		for(int i = 0; i < constructors.length; i++){
			System.out.print("\t");
			Constructor constructor = constructors[i];
			Class[] paramTypes = constructor.getParameterTypes();
			String constructorName = constructor.getName();
			System.out.print(Modifier.toString(constructor.getModifiers()));
			System.out.print(" " + constructorName + "(");
			for(int j = 0; j < paramTypes.length; j++){
				if(j > 0){
					System.out.print(", ");
				}
				System.out.print(paramTypes[j].getName());
			}
			System.out.print(");\n");
		}
	}
	
	public static void printMethods(Class cls){
		Method[] methods = cls.getDeclaredMethods();
		for(int i = 0; i < methods.length; i++){
			System.out.print("\t");
			Method method = methods[i];
			Class returnType = method.getReturnType();
			Class[] paramTypes = method.getParameterTypes();
			String methodName = method.getName();
			System.out.print(Modifier.toString(method.getModifiers()));
			System.out.print(" " + returnType.getName() + " " + methodName + "(");
			for(int j = 0; j < paramTypes.length; j++){
				if(j > 0){
					System.out.print(", ");
				}
				System.out.print(paramTypes[j].getName());
			}
			System.out.print(");\n");
		}
	}
	
	public static void printFields(Class cls){
		Field[] fields = cls.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			System.out.print("\t");
			Field field = fields[i];
			Class type = field.getType();
			String fieldName = field.getName();
			System.out.print(Modifier.toString(field.getModifiers()));
			System.out.print(" " + type.getName() + " " + fieldName + ";\n");
		}
	}
}
