package com.hms.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * zuul网关过滤器
 *      zuul不仅只是路由，并且还能过滤，做一些安全验证。
 *      可以通过shouldFilter()方法返回值为false，来标明过滤器是否起作用
 */
@Component
public class HmsZuulFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(HmsZuulFilter.class);

    /**
     * filter类型。
     *      分为以下几种：
     *          pre: 可以在请求被路由之前调用
     *          route: 在路由请求时候被调用
     *          post: 在routing和error过滤器之后被调用
     *          error: 处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter执行顺序，通过数字指定
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * filter是否需要执行 true执行 false 不执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return false;
    }

    /**
     * filter具体逻辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        log.info(String.format("自定义zuul过滤器拦截请求：%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        //这里就简单的判断一下是否有token参数，只作为测试
        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
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