package cn.sh.util.thread;

public class ThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++) {
			System.out.println(Thread.currentThread().getName()+"???????"+"i="+i);
		MyThread thread1=new MyThread(i);
		MyThread thread2=new MyThread(i);
		thread1.start();
		thread2.start();
		}
		

	}

}
