package cn.sh.util.anno;

public class Student {
	
	@MyAnnotation(name="zhangsan")
	public void printName(String name) {
		System.out.println("学生的名字为："+name);
	}

}
