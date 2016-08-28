package com.vipshot.sdk;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;

import com.vipshot.config.ZookeeperConfiguration;

public class NestedZookeeperSdk {

	private ZookeeperConfiguration zkConfig;

	private TestingServer nestedServer = null;
	
	private CuratorFramework client;
	
	public NestedZookeeperSdk(ZookeeperConfiguration zkConfig) {
		this.zkConfig = zkConfig;
	}
	
	public synchronized TestingServer startAndGetNestedServer(final int port, final String dataDir) throws Exception {
		if (nestedServer == null) {
			try {
				nestedServer = new TestingServer(port, new File(dataDir));
			} catch (Exception e) {
				throw e;
			}
		}
		return nestedServer;
	}
	
	public void closeNestedServer() throws IOException {
		nestedServer.close();
	}
	
	
}
