package ben.li;

import com.dangdang.ddframe.job.api.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.api.type.simple.api.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.bootstrap.JobScheduler;
import com.dangdang.ddframe.job.lite.api.config.LiteJobConfiguration;
import com.dangdang.ddframe.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.reg.zookeeper.ZookeeperRegistryCenter;

public class Main {
	private final ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(
			"10.199.200.53:2181", "myjob");

	private final CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
			zkConfig);

	public static void main(String[] args) {
		new Main().init();
		// 定义作业核心配置配置
	}

	private void init() {
//		zkConfig.setNestedPort(4181);
//		zkConfig.setNestedDataDir(String.format("target/test_zk_data/%s/",
//				System.nanoTime()));
		regCenter.init();
		JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration
				.newBuilder("MyElasticJob", "0/3 * * * * ?", 10).build();
		// 定义SIMPLE类型
		SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(
				simpleCoreConfig, MyElasticJob.class.getCanonicalName());
		
		new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(
				simpleJobConfig).build()).init();
	}

}
