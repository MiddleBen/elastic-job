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
	 * 默认zk失败重试间隔时间
	 */
	private int defaultRetryMs = 1000;

	/**
	 * 最大重试时间
	 */
	private int maxRetryMs = 3000;
	
	/**
	 * 最大重试次数
	 */
	private int maxRetries;
	
	/**
	 * 回话超时时间
	 */
	private int sessionTimeoutMs;
	
	/**
	 * 连接超时时间
	 */
	private int connectionTimeoutMs;
	
	/**
     * 本地属性文件路径
     */
    private String localPropertiesPath;
    
    /**
     * 是否允许本地值覆盖注册中心
     */
    private boolean overwrite;

	public String getLocalPropertiesPath() {
		return localPropertiesPath;
	}

	public void setLocalPropertiesPath(String localPropertiesPath) {
		this.localPropertiesPath = localPropertiesPath;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

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

	public int getDefaultRetryMs() {
		return defaultRetryMs;
	}

	public void setDefaultRetryMs(int defaultRetryMs) {
		this.defaultRetryMs = defaultRetryMs;
	}

	public int getMaxRetryMs() {
		return maxRetryMs;
	}

	public void setMaxRetryMs(int maxRetryMs) {
		this.maxRetryMs = maxRetryMs;
	}

	public int getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}
}
