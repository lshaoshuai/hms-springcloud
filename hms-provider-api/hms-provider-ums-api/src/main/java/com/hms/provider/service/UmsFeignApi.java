package com.hms.provider.service;

import com.hms.provider.service.hystrix.UmsFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author luoshao
 * @date 2019/6/20 20:16
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(value = "hms-provider-ums",  fallback = UmsFeignHystrix.class)
public interface UmsFeignApi {
}
