package concurrent.cyclebarry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
public class CyclicBarrierTest {

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		CyclicBarrier barrier = new CyclicBarrier(5);// 两个工人的协作
		Worker worker1 = new Worker("zhang san", barrier);
		Worker worker2 = new Worker("li si", barrier);
		worker1.start();//
		worker2.start();//
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					barrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 等待所有工人完成工作
				catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		barrier.await();// 等待所有工人完成工作
		System.out.println("all work done at " + sdf.format(new Date()));
	}

	static class Worker extends Thread {
		String workerName;
		CyclicBarrier barrier;

		public Worker(String workerName, CyclicBarrier latch) {
			this.workerName = workerName;
			this.barrier = latch;
		}

		public void run() {
			System.out.println("Worker " + workerName + " do work complete at " + sdf.format(new Date()));
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}// 工人完成工作，计数器减一
		}

	}


}
