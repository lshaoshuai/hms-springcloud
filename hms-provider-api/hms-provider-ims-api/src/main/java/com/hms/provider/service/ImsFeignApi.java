package com.hms.provider.service;

import com.hms.provider.service.hystrix.ImsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author luoshao
 * @date 2019/6/22 13:32
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@FeignClient(name = "hms-service-stock" ,fallback = ImsFeignHystrix.class)
public interface ImsFeignApi {

    @GetMapping(value =  "rpc/ims/local/count")
    Wrapper GetStockCount();
}
