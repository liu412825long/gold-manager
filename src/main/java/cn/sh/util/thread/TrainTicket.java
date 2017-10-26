package cn.sh.util.thread;

public class TrainTicket implements Runnable{
	
	 int num= 100;
	 private String str=new String("");
	
	public void run() {
		while(true) {
			synchronized(str) {
				sale();
				
			}
		}
	}
	
	public  void sale() {
		if(num>0) {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"这是第"+num--+"张火车票。");
		}
		
	}

}
