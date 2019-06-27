package com.hms.provider.service;

import com.hms.provider.service.hystrix.HbsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luoshao
 * @date 2019/6/22 13:32
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@FeignClient(name = "hms-service-hotel" ,fallback = HbsFeignHystrix.class)
public interface HbsFeignApi {

    @GetMapping(value =  "/local/floor")
    Wrapper getFloorById(@RequestParam("hotelid") int hotelid);
}
