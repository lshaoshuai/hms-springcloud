package com.hms.provider.service;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.provider.model.dto.OrderFrontDto;
import com.hms.provider.service.hystrix.OmsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author luoshao
 * @date 2019/6/25 22:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(name = "hms-service-order",  fallback = OmsFeignHystrix.class)
public interface OmsFeignApi {

    @GetMapping(value =  "/rpc/local/count")
    @NoNeedAccessAuthentication
    Wrapper getOrderCount();

    @PostMapping("/rpc/local/commit")
    @NoNeedAccessAuthentication
    Wrapper commitLocalOrder(OrderFrontDto orderFrontDto);
}
