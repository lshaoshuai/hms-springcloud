package com.hms.provider.web.rpc;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.service.HbsFeignApi;
import com.hms.provider.service.HotelQueryService;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/6/24 16:14
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RefreshScope
@RestController
@Api(value = "API - HbsFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/local")
public class HbsFeignClient extends BaseController implements HbsFeignApi {

    @Resource
    private HotelQueryService hotelQueryService;

    @GetMapping("/floor")
    @ApiOperation(httpMethod = "GET", value = "获取酒店楼层数")
    @NoNeedAccessAuthentication
    public Wrapper getFloorById(@RequestParam("hotelid") int hotelid){
        int floor = hotelQueryService.getHotelFloorInfo(hotelid);
        logger.info("获取到的楼层数为：{}", floor);
        return WrapMapper.ok(floor);
    }

}
