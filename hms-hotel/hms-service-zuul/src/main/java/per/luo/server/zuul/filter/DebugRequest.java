package per.luo.server.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Component
public class DebugRequest extends ZuulFilter {
	
	public final static String CUR_COOKIE = "cur_request_cookies"; 
	private Logger log = LoggerFactory.getLogger(LoginPreFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
    	
    	//记录请求的cookie
    	Map<String, String> curReqCookies = new HashMap<String, String>();
    	Map<String, String> curReqParam = new HashMap<String, String>();
    	//操作response,不能直接操作,得通过上下文，为什么？ 
    	//问题  1：有多少种方法获取到请求
    	RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        log.info("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
        log.info("REQUEST:: " + req.getScheme() + " " + req.getRemoteAddr() + ":" + req.getRemotePort());
        StringBuilder params = new StringBuilder("?");
        Enumeration<String> names = req.getParameterNames();
        if( req.getMethod().equals("GET") ) {
           while (names.hasMoreElements()) {
              String name = (String) names.nextElement();
              params.append(name);
              params.append("=");
              params.append(req.getParameter(name));
              params.append("&");
           }
        }
       
        if (params.length() > 0) {
            params.delete(params.length()-1, params.length());
        }
        log.info("REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + params + " " + req.getProtocol());

        Enumeration<String> headers = req.getHeaderNames();

        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = req.getHeader(name);
            
            curReqCookies.put(name, value);
            log.info("REQUEST:: > " + name + ":" + value);
        }

        if (!ctx.isChunkedRequestBody()) {
            ServletInputStream inp = null;
            try {
                inp = ctx.getRequest().getInputStream();
                String body = null;
                if (inp != null) {
                    body = IOUtils.toString(inp);
                    log.info("REQUEST:: > " + body);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        ctx.put(CUR_COOKIE, curReqCookies);
        return null;
    }

}