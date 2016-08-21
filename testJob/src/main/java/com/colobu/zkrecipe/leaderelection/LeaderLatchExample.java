package com.colobu.zkrecipe.leaderelection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;

import com.google.common.collect.Lists;

/**
 * 自己修改來測試用的選舉主節點代碼
 * @author ben
 *
 */
public class LeaderLatchExample {
	
	private static final int CLIENT_QTY = 2;
	private static final String PATH = "/myjob/MyElasticJob/leader/election/latch";

	public static void main(String[] args) throws Exception {
		List<CuratorFramework> clients = Lists.newArrayList();
		List<LeaderLatch> examples = Lists.newArrayList();
		TestingServer server = new TestingServer();
		try {
			for (int i = 0; i < CLIENT_QTY; ++i) {
				CuratorFramework client = CuratorFrameworkFactory.newClient("10.199.200.53:2181",
						new ExponentialBackoffRetry(1000, 3));
				client.start();
				clients.add(client);
				try (LeaderLatch latch = new LeaderLatch(client, PATH)) {
					examples.add(latch);
		            latch.start();
		            latch.await();
		        //CHECKSTYLE:OFF
		        } catch (final Exception ex) {
		        //CHECKSTYLE:ON
		        	ex.printStackTrace();
		        }
//				LeaderLatch example = new LeaderLatch(client, PATH);
//				examples.add(example);
//				example.start();
//				example.await();
			}
//			Thread.sleep(1000);
			LeaderLatch currentLeader = null;
			for (int i = 0; i < CLIENT_QTY; ++i) {
				LeaderLatch example = examples.get(i);
				if (example.hasLeadership())
					currentLeader = example;
			}
			System.out.println("current leader is " + currentLeader.getId());
			System.out.println("release the leader " + currentLeader.getId());
//			currentLeader.close();
//			examples.get(0).await(2, TimeUnit.SECONDS);
//			System.out.println("Client #0 maybe is elected as the leader or not although it want to be");
//			System.out.println("the new leader is " + examples.get(0).getLeader().getId());
//
//			System.out.println("Press enter/return to quit\n");
			new BufferedReader(new InputStreamReader(System.in)).readLine();
			currentLeader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Shutting down...");
			for (LeaderLatch exampleClient : examples) {
				CloseableUtils.closeQuietly(exampleClient);
			}
			for (CuratorFramework client : clients) {
				CloseableUtils.closeQuietly(client);
			}
			CloseableUtils.closeQuietly(server);
		}
	}
}