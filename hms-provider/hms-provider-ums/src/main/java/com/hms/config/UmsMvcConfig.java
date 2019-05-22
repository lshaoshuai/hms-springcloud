package com.hms.config;

import com.hms.core.config.SwaggerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author luoshao
 * @date 2019/5/20 20:54
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Configuration
@EnableWebMvc
@Import(SwaggerConfiguration.class) //引入配置类
public class UmsMvcConfig extends WebMvcConfigurerAdapter {

//    /** 解决跨域问题 **/
//    public void addCorsMappings(CorsRegistry registry) ;
//
//    /** 添加拦截器 **/
//    void addInterceptors(InterceptorRegistry registry);
//
//    /** 这里配置视图解析器 **/
//    void configureViewResolvers(ViewResolverRegistry registry);
//
//    /** 配置内容裁决的一些选项 **/
//    void configureContentNegotiation(ContentNegotiationConfigurer configurer);
//
//    /** 视图跳转控制器 **/
//    void addViewControllers(ViewControllerRegistry registry);
//
//    /** 静态资源处理 **/
//    void addResourceHandlers(ResourceHandlerRegistry registry);
//
//    /** 默认静态资源处理器 **/
//    void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/");
    }

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/cors/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET")
                .allowedOrigins("*");
    }

}
