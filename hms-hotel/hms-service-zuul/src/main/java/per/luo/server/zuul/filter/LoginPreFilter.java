package per.luo.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;


@Component
public class LoginPreFilter extends ZuulFilter{
	
	private Logger log = LoggerFactory.getLogger(LoginPreFilter.class);
	/**
	 * 根据url判断是否进去登录，如果不是进去登录就不需要过滤
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
    	RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();
        //?? 问题：这些东西怎么才能不写死
        return !requestUrl.contains("login") && !requestUrl.contains("join");
	}

	
    /**
     * filter类型。
     *   	   分为以下几种：
     *          pre:请求执行之前filter
     *          route: 处理请求，进行路由
     *          post: 请求处理完成后执行的filter
     *          error:出现错误时执行的filter
     * @return
     */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
	
	//过滤器的具体逻辑
	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        log.info(String.format("login过滤器拦截请求：%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        // 判断是否有token，如果没有token的话，就直接返回
        Object accessToken = request.getParameter("token");
    	HashMap<String, String>  curReqCookies= (HashMap<String, String>) ctx.get(DebugRequest.CUR_COOKIE);
    	String tokenInCookie = curReqCookies.get("token");
        
    	if(accessToken == null && tokenInCookie == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                HttpServletResponse res = ctx.getResponse();
                res.getWriter().write("token is empty");
            }catch (Exception e){}

            return null;
        }
        log.info("自定义zuul过滤器处理完成 ok！");
        return null;
	}
}
