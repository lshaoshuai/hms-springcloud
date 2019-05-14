package per.luo.server.zuul.filter;

import com.google.gson.Gson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

public class LoginAfterFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public Object run() {
		//1、获取请求的响应
		RequestContext ctx = RequestContext.getCurrentContext();
		
		// responseDataStream和ResponseBody的区别
		InputStream responseDataStream = ctx.getResponseDataStream();
		
		//2、流 -> 字符串 -> 对象
		String body = null;
		try {
			body = StreamUtils.copyToString(responseDataStream, Charset.forName("utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (StringUtils.isNotBlank(body)) {
             Gson gson = new Gson();
             @SuppressWarnings("unchecked")
             Map<String, String> result = gson.fromJson(body, Map.class);
             
             //3、获取token放入到cookie中,应该不需要，因为框架也想到这个问题了
             if (StringUtils.isNotBlank(result.get("token"))) {
            	 String cookie = ctx.getResponse().getHeader("cookies");
             }
		 }
		 
		return null;
	}

}
