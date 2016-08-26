package com.vipshot.config;

public final class ZookeeperConfiguration {

	public ZookeeperConfiguration(String serverLists, String namespace) {
		super();
		this.serverLists = serverLists;
		this.namespace = namespace;
	}

	private String serverLists;

	private String namespace;

	/**
	 * 默认失败重试时间
	 */
	private int defaultRetryMilliseconds = 1000;

	/**
	 * 最大重试时间
	 */
	private int maxRetryMilliseconds = 3000;

	public String getServerLists() {
		return serverLists;
	}

	public void setServerLists(String serverLists) {
		this.serverLists = serverLists;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public int getDefaultRetryMilliseconds() {
		return defaultRetryMilliseconds;
	}

	public void setDefaultRetryMilliseconds(int defaultRetryMilliseconds) {
		this.defaultRetryMilliseconds = defaultRetryMilliseconds;
	}

	public int getMaxRetryMilliseconds() {
		return maxRetryMilliseconds;
	}

	public void setMaxRetryMilliseconds(int maxRetryMilliseconds) {
		this.maxRetryMilliseconds = maxRetryMilliseconds;
	}

}
