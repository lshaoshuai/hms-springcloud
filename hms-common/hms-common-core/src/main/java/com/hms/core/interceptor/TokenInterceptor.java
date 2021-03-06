package com.hms.core.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hms.RedisKeyUtil;
import com.hms.ThreadLocalMap;
import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.base.constant.GlobalConstant;
import com.hms.base.dto.UserTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * The class Token interceptor.
 *
 * @author paascloud.net @gmail.com
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

	@Value("${hms.oauth2.jwtSigningKey}")
	private String jwtSigningKey;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	private static final String OPTIONS = "OPTIONS";
	private static final String AUTH_PATH1 = "/auth";
	private static final String AUTH_PATH2 = "/oauth";
	private static final String AUTH_PATH3 = "/error";
	private static final String AUTH_PATH4 = "/api";
	private static final String AUTH_PATH5 = "/localroom";

	/**
	 * After completion.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param ex       the ex
	 *
	 * @throws Exception the exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception ex) throws Exception {
		if (ex != null) {
			log.error("<== afterCompletion - 解析token失败. ex={}", ex.getMessage(), ex);
			this.handleException(response);
		}
	}

	/**
	 * Post handle.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param arg2     the arg 2
	 * @param mv       the mv
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView mv) {
	}

	/**
	 * Pre handle boolean.
	 *
	 * @param request  the request
	 * @param response the response
	 * @param handler  the handler
	 *
	 * @return the boolean
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		String uri = request.getRequestURI();
		log.info("<== preHandle - 权限拦截器.  url={}", uri);
		if (uri.contains(AUTH_PATH1) || uri.contains(AUTH_PATH2) || uri.contains(AUTH_PATH3) || uri.contains(AUTH_PATH4)) {
			log.info("<== preHandle - 配置URL不走认证.  url={}", uri);
			return true;
		}
		log.info("<== preHandle - 调试模式不走认证.  OPTIONS={}", request.getMethod().toUpperCase());

		if (OPTIONS.equalsIgnoreCase(request.getMethod())) {
			log.info("<== preHandle - 调试模式不走认证.  url={}", uri);
			return true;
		}

		if (isHaveAccess(handler)) {
			log.info("<== preHandle - 不需要认证注解不走认证.  token={}");
			return true;
		}
//		if(uri.contains(AUTH_PATH5))
//		{
//			log.info("<== preHandle - 本地用户拦截器已拦截");
//			String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
//			log.info("<== preHandle - 权限拦截器.  token={}", token);
//			log.info("Redis的Json数据{}",redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token)));
//			LocalUserTokenDto localUserTokenDto =  JSON.toJavaObject((JSONObject)redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token)),LocalUserTokenDto.class);
//			if (localUserTokenDto == null) {
//				log.error("获取用户信息失败, 不允许操作");
//				return false;
//			}
//			log.info("<== preHandle - 权限拦截器.  loginUser={}", localUserTokenDto);
//			ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, localUserTokenDto);
//			log.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, localUserTokenDto);
//			return true;
//		}

		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "Bearer ");
		log.info("<== preHandle - 权限拦截器.  token={}", token);
		log.info("Redis的Json数据{}",redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token)));
		//UserTokenDto userTokenDto =  (UserTokenDto)redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token));
		UserTokenDto userTokenDto =  JSON.toJavaObject((JSONObject)redisTemplate.opsForValue().get(RedisKeyUtil.getAccessTokenKey(token)),UserTokenDto.class);
		if (userTokenDto == null) {
			log.error("获取用户信息失败, 不允许操作");
			return false;
		}
		log.info("<== preHandle - 权限拦截器.  loginUser={}", userTokenDto);
		ThreadLocalMap.put(GlobalConstant.Sys.TOKEN_AUTH_DTO, userTokenDto);
		log.info("<== preHandle - 权限拦截器.  url={}, loginUser={}", uri, userTokenDto);
		return true;
	}

	private void handleException(HttpServletResponse res) throws IOException {
		System.out.println("handleException-----------------------");
		res.resetBuffer();
		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"code\":100009 ,\"message\" :\"解析token失败\"}");
		res.flushBuffer();
	}

	private boolean isHaveAccess(Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		Method method = handlerMethod.getMethod();

		NoNeedAccessAuthentication responseBody = AnnotationUtils.findAnnotation(method, NoNeedAccessAuthentication.class);
		return responseBody != null;
	}

}
  