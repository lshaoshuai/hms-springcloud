package com.hms.config;

import com.hms.core.interceptor.TokenInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/5/17 18:35
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
//@Configuration
//@EnableWebMvc
public class RmsMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private TokenInterceptor vueViewInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
    }

    /**
     * Add interceptors.
     *
     * @param registry the registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(vueViewInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/pay/alipayCallback", "/swagger-resources/**", "*.js", "/**/*.js", "*.css", "/**/*.css", "*.html", "/**/*.html");
    }

}
