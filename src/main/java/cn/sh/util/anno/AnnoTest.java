package cn.sh.util.anno;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Class<Student> stu=Student.class;
		Method[] methods=stu.getMethods();
		for(Method me:methods) {
			Annotation[] anno=me.getAnnotations();
			for(Annotation a:anno) {
				if(a instanceof MyAnnotation) {
					
					System.out.println(((MyAnnotation) a).name());
					System.out.println(me.getName());
				}
			}
		}

	}

}
