package concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
	
	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		LockSupport.unpark(thread);//释放许可
		LockSupport.unpark(thread);//释放许可
		LockSupport.unpark(thread);//释放许可
		LockSupport.park();
		LockSupport.park();
	    System.out.println("block.");  
	}

}
