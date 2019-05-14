package per.luo.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;


/**
 * 示例的过滤器，有一些问题都记录在这里
 * @author 骚哥
 *
 */
public class IndexFilter extends ZuulFilter{
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * 越小的值越优先处理
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
