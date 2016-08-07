package ben.li;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

class MyElasticJobListener implements ElasticJobListener {
    
	public void beforeJobExecuted(ShardingContext shardingContext) {
		System.out.println("开始执行作业： " + JSON.toJSONString(shardingContext));
	}

	public void afterJobExecuted(ShardingContext shardingContext) {
		System.out.println("结束执行作业： " + JSON.toJSONString(shardingContext));		
	}
}