package per.luo.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;


//@Component  // 该注解是暴露给spring的
public class DebugResponse extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public String filterType() {
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public Object run() {
		// 问题：1：怎么获取到response值
		return null;
	}
}
