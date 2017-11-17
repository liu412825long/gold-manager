package cn.sh.util.deadlock;

public class DeadLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1;i<100;i++) {
			System.out.print("循环次数："+i);
			new Thread(new SynAddRunnable(1, 2)).start();
			new Thread(new SynAddRunnable(2, 1)).start();
		}

	}

}
