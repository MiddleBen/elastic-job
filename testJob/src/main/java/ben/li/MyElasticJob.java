package ben.li;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.type.simple.api.SimpleJob;

public class MyElasticJob implements SimpleJob {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void execute(ShardingContext shardingContext) {
		System.out.println(sdf.format(new Date()) + " - shardingContext = "
				+ JSON.toJSONString(shardingContext));
	}

}