package ben.li;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.type.simple.api.SimpleJob;

public class MyElasticJob implements SimpleJob {

	public void execute(ShardingContext shardingContext) {
		System.out.println("shardingContext = "
				+ JSON.toJSONString(shardingContext));
	}

}