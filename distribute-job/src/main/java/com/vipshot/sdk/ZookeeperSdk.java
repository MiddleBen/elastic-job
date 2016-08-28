package com.vipshot.sdk;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorFrameworkFactory.Builder;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.OperationTimeoutException;

import com.google.common.base.Strings;
import com.vipshot.config.ZookeeperConfiguration;
import com.vipshot.util.PropertyUtils;

public class ZookeeperSdk {

	private ZookeeperConfiguration zkConfig;

	private CuratorFramework client;
	
	public ZookeeperSdk(ZookeeperConfiguration zkConfig) {
		this.zkConfig = zkConfig;
	}
	
	/**
	 * 设置各种连接参数，初始化zk客户端
	 * @throws KeeperException 
	 * @throws IOException 
	 */
	public void init() throws KeeperException, IOException {
		Builder curatorBuilder = CuratorFrameworkFactory.builder().connectString(zkConfig.getServerLists())
		.retryPolicy(
				new BoundedExponentialBackoffRetry(zkConfig.getDefaultRetryMs(), zkConfig.getMaxRetries(), zkConfig.getMaxRetryMs())
				)
		.namespace(zkConfig.getNamespace())
		.connectionTimeoutMs(zkConfig.getConnectionTimeoutMs())
		.sessionTimeoutMs(zkConfig.getSessionTimeoutMs());
		client = curatorBuilder.build();
		client.start();
		try {
			client.blockUntilConnected(zkConfig.getConnectionTimeoutMs(), TimeUnit.MILLISECONDS);
			if (client.getZookeeperClient().isConnected()) {
				throw new KeeperException.OperationTimeoutException();
			}
			if (!Strings.isNullOrEmpty(zkConfig.getLocalPropertiesPath())) {
				PropertyUtils.loadLocalProperties(zkConfig.getLocalPropertiesPath());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
