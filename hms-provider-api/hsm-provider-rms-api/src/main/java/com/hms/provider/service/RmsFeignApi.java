package com.hms.provider.service;

import com.hms.provider.service.hystrix.RmsFeignHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author luoshao
 * @date 2019/6/23 21:33
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(value = "hms-provider-rms",  fallback = RmsFeignHystrix.class)
public class RmsFeignApi {

}
