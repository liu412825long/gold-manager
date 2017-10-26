package cn.sh.util.thread;

public class MyRunnable implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" runnable is running?????");
		}
		
	}

}
