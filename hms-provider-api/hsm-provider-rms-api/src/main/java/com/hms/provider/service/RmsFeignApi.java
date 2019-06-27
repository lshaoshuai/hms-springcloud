package com.hms.provider.service;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.provider.service.hystrix.RmsFeignHystrix;
import com.hms.wrapper.Wrapper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author luoshao
 * @date 2019/6/23 21:33
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@FeignClient(name = "hms-service-room",  fallback = RmsFeignHystrix.class)
public interface RmsFeignApi {

    @GetMapping(value =  "/rpc/local/count")
    @NoNeedAccessAuthentication
    Wrapper getRoomCount(@RequestParam("hotelid") int hotelid);

    @GetMapping(value =  "/rpc/local/type")
    @NoNeedAccessAuthentication
    Wrapper getRoomTypeInfo(@RequestParam("id") int id);

    @GetMapping(value =  "/rpc/local/room")
    @NoNeedAccessAuthentication
    Wrapper getLocalRoomInfo(@RequestParam("id") int id);

    @PostMapping(value =  "/rpc/local/update")
    @NoNeedAccessAuthentication
    Wrapper updateLocalRoomInfo(@RequestParam("id") int roomid,@RequestParam("id") int status);
}
