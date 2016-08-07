package ben.li;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;

public final class TestDistributeOnceElasticJobListener extends AbstractDistributeOnceElasticJobListener {
    
    public TestDistributeOnceElasticJobListener(final long startTimeoutMills, final long completeTimeoutMills) {
        super(startTimeoutMills, completeTimeoutMills);
    }
    
	@Override
	public void doBeforeJobExecutedAtLastStarted(ShardingContext shardingContext) {
		System.out.println("分布式环境开始执行作业： " + JSON.toJSONString(shardingContext));		
	}

	@Override
	public void doAfterJobExecutedAtLastCompleted(ShardingContext shardingContext) {
		System.out.println("分布式环境开始执行作业： " + JSON.toJSONString(shardingContext));		
	}
}