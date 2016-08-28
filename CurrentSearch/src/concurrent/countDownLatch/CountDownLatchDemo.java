package concurrent.countDownLatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);// 两个工人的协作
		Worker worker1 = new Worker("zhang san", latch);
		Worker worker2 = new Worker("li si", latch);
		worker1.start();//
		worker2.start();//
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					latch.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 等待所有工人完成工作
			}
		}).start();
		latch.await();// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
	}

	static class Worker extends Thread {
		String workerName;
		CountDownLatch latch;

		public Worker(String workerName, CountDownLatch latch) {
			this.workerName = workerName;
			this.latch = latch;
		}

		public void run() {
			System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
			latch.countDown();// 工人完成工作，计数器减一
		}

	}

}
