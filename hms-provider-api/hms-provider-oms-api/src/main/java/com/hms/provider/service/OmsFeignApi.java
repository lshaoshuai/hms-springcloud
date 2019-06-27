package com.hms.provider.service;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.provider.service.hystrix.OmsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luoshao
 * @date 2019/6/25 22:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(name = "hms-service-order",  fallback = OmsFeignHystrix.class)
public interface OmsFeignApi {

    @GetMapping(value =  "/local/count")
    @NoNeedAccessAuthentication
    Wrapper getOrderCount();
}
