package cn.sh.util.thread;

import java.io.UnsupportedEncodingException;

import cn.sh.util.thread.MyRunnable;;
public class ThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
//		test3();
		test4();
	}
	
	/**
	 * 
	 */
	private static void test4() {
		int a=12; //1100
		int b=-12; //11111111111111111111111111110100
		int c=a>>1;
		int d=b>>1;// 11111111111111111111111111111010
		int e=b>>>1;//01111111111111111111111111111010
		System.out.println(Integer.toBinaryString(a));
		System.out.println(c+">>"+Integer.toBinaryString(c));
		System.out.println(d+">>"+Integer.toBinaryString(d));
		System.out.println(e+">>"+Integer.toBinaryString(e));
	}
	
	private static void test3() {
		TrainTicket tt=new TrainTicket();
		Thread t=new Thread(tt);
		t.start();
		int i=0;
		while(true) {
			if(i==50) {
				try {
					//join方法的作用是：将t所对应的线程合并到test3的主线程中，故执行到i=49的时候，就不在打印main Thread:了
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("main Thread:"+Thread.currentThread().getName()+"i="+i);
			i++;
		}
	}
	
	private static void test2() {
		// 这样的话会出现一张火车票打印出来2次
		TrainTicket tt=new TrainTicket();
		Thread t=new Thread(tt);
		t.start();
		Thread t1=new Thread(tt);
		t1.start();
		Thread t2=new Thread(tt);
		t2.start();
		Thread t3=new Thread(tt);
		t3.start();
	}
	
	private static void test1() {
		new MyThread().start();
		MyRunnable run=new MyRunnable();
		Thread t=new Thread(run);
		t.start();
	}

}
