package concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	
	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				LockSupport.park(thread);
				System.out.println("unpark " + Thread.currentThread().getName());
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				LockSupport.park(thread);
				System.out.println("unpark " + Thread.currentThread().getName());
			}
		});
		t1.start();
		t2.start();
		LockSupport.unpark(t1);//释放许可
	    System.out.println("block.");  
	}

}
