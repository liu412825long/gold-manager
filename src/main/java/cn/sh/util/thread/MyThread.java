package cn.sh.util.thread;

public class MyThread extends Thread {
	
	private int i;
	
	public MyThread(int i) {
		this.i=i;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++) {
		System.out.println(Thread.currentThread().getName()+"i="+i+"....."+i);
		}
	}

}
