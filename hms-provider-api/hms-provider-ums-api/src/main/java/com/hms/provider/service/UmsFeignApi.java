package com.hms.provider.service;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.provider.model.dto.CustomerDto;
import com.hms.provider.service.hystrix.UmsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luoshao
 * @date 2019/6/20 20:16
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(value = "hms-service-user",  fallback = UmsFeignHystrix.class)
public interface UmsFeignApi {

    @PostMapping(value =  "/rpc/ums/local/custom")
    @NoNeedAccessAuthentication
    Wrapper addCustomerInfo(@RequestBody CustomerDto customerDto);

    @PostMapping(value =  "/rpc/ums/local/custom/search")
    @NoNeedAccessAuthentication
    Wrapper queryCustomerInfo(@RequestParam("userid") String userid);
}
