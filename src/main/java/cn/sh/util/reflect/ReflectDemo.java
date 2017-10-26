package cn.sh.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectDemo {
	
	public static void main(String[] args)throws Exception {
		test1();
	}
	
	public static void test1() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException {
		Class<?> demoClass=Class.forName("cn.sh.util.ReflectEntity");
		System.out.println("反射获取到类的名称："+demoClass.getName());
		//该方法只获取到 public 修饰的变量
		Field[] fields=demoClass.getFields();
		for(Field f:fields) {
			System.out.println("public修饰的变量类型："+f.getType()+"，变量名："+f.getName());
		}
		// 获取到所有变量
		Field[] allFields=demoClass.getDeclaredFields();
		for(Field f:allFields) {
			System.out.println("所有的变量类型："+f.getType()+"，变量名："+f.getName());
		}
		// 获取public方法，包括父类的方法,不包括private方法
		Method[] methods=demoClass.getMethods();
		for(Method m:methods) {
			System.out.println("public方法："+m.getName());
		}
		
		
		// 获取所有的方法，不包括父类的方法，包括private方法
		Method[] allMethods=demoClass.getDeclaredMethods();
		for(Method m:allMethods) {
			System.out.println("所有的方法："+m.getName()+",方法的返回值类型："+m.getReturnType());
		}
		
		Constructor<?>[] constructors=demoClass.getConstructors();
		for(Constructor<?> c:constructors) {
			System.out.println("构造方法："+c.getName());
			Parameter[] parameters=c.getParameters();
			for(Parameter p:parameters) {
				System.out.println("构造方法的参数类型："+p.getType()+",参数名："+p.getName());
			}
		}
		
		Field field=demoClass.getField("name");
		ReflectEntity entity=(ReflectEntity)demoClass.newInstance();
		System.out.println(ReflectEntity.name);
		field.set(entity, "liuhailong");
		System.out.println(ReflectEntity.name);
		
		Annotation[] annotations=demoClass.getAnnotations();
		for(Annotation a:annotations) {
			System.out.println("public类型的注解："+a);
		}
		Annotation[] allAnnotations=demoClass.getDeclaredAnnotations();
		for(Annotation a:allAnnotations) {
			System.out.println("所有的注解："+a);
			if(a instanceof MyAnnotation) {
				MyAnnotation myAnnotation=(MyAnnotation)a;
				System.out.println("name:"+myAnnotation.name()+",value:"+myAnnotation.value());
			}
		}
		
		
	}

}
