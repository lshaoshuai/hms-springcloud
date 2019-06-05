package com.hms.core.config;

import com.hms.core.interceptor.TokenInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@Configuration
public class CoreConfiguration {
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@ConditionalOnMissingBean(HandlerInterceptor.class)
	@ConditionalOnProperty(prefix = "hms.token.interceptor", name = "enable", havingValue = "true")
	public TokenInterceptor tokenInterceptor() {
		return new TokenInterceptor();
	}
}
