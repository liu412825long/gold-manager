package cn.sh.util.deadlock;

public class SynAddRunnable implements Runnable {
	
	int a,b;
	
	public SynAddRunnable(int a,int b) {
		this.a=a;
		this.b=b;
	}

	public void run() {
		// TODO Auto-generated method stub
		synchronized(Integer.valueOf(a)) {
			synchronized(Integer.valueOf(b)) {
				System.out.println(Thread.currentThread().getName()+"a+b="+(a+b));
			}
		}
		
	}

}
