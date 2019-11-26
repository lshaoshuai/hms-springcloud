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

    @GetMapping(value =  "/rpc/rms/local/count")
    @NoNeedAccessAuthentication
    Wrapper getRoomCount(@RequestParam("hotelid") int hotelid);

    @GetMapping(value =  "/rpc/rms/local/type")
    @NoNeedAccessAuthentication
    Wrapper getRoomTypeInfo(@RequestParam("id") int id);

    @GetMapping(value =  "/rpc/rms/local/room")
    @NoNeedAccessAuthentication
    Wrapper getLocalRoomInfo(@RequestParam("id") int id);

    @PostMapping(value =  "/rpc/rms/local/update")
    @NoNeedAccessAuthentication
    Wrapper updateLocalRoomInfo(@RequestParam("roomId") int roomId,@RequestParam("checkInStatus") int checkInStatus);

    @GetMapping(value =  "/rpc/rms/local/dynamic/count")
    @NoNeedAccessAuthentication
    Wrapper getEmptyRoomCount(@RequestParam("hotelid") int hotelid,@RequestParam("status") int status,@RequestParam("type") String type,@RequestParam("value") String value);

    @GetMapping(value =  "/rpc/rms/local/price")
    @NoNeedAccessAuthentication
    Wrapper getRoomPrice(@RequestParam("roomid") int roomid);

    @GetMapping(value =  "/rpc/rms/local/random")
    @NoNeedAccessAuthentication
    Wrapper getRandomRoom(@RequestParam("roomname") String roomname);
}
