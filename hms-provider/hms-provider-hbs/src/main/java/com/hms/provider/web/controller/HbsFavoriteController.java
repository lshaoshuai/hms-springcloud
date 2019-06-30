package com.hms.provider.web.controller;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.base.dto.UserTokenDto;
import com.hms.core.support.BaseController;
import com.hms.provider.service.HotelFavoriteService;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/6/29 10:28
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/favorite")
@Api(description = "酒店信息接口",value = "WEB - HbsAppController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //表示标识的这个类是swagger的资源
@Validated
public class HbsFavoriteController extends BaseController {

    @Resource
    HotelFavoriteService hotelFavoriteService;

    @PostMapping("/set/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "修改酒店信息")
    @HystrixCommand(fallbackMethod = "httpError")
    @NoNeedAccessAuthentication
    public Wrapper setFavoriteHotel(@PathVariable int hotelid){

        UserTokenDto userTokenDto = getLoginAuthDto();
        boolean is_success = hotelFavoriteService.setFavoriteHotel(hotelid,userTokenDto.getId());
        return WrapMapper.ok(is_success);
    }

    @PostMapping("/cancel/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "修改酒店信息")
    @HystrixCommand(fallbackMethod = "httpError")
    @NoNeedAccessAuthentication
    public Wrapper cancelFavoriteHotel(@PathVariable int hotelid){
        UserTokenDto userTokenDto = getLoginAuthDto();
        boolean is_success = hotelFavoriteService.cancelFavoriteHotel(hotelid,userTokenDto.getId());
        return WrapMapper.ok(is_success);
    }

    public Wrapper httpError(int i) {
        return WrapMapper.ok("" + i);
    }
}
