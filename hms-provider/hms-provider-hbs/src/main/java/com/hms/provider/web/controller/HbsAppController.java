package com.hms.provider.web.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.service.HotelQueryService;
import com.hms.provider.vo.HotelInfoVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @date 2019/5/18 19:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/hotel")
@Api(description = "酒店信息接口",value = "WEB - HbsAppController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //表示标识的这个类是swagger的资源
@Validated
public class HbsAppController extends BaseController {

    @Resource
    private HotelQueryService hotelQueryService;

    @PostMapping("/info/{index}/{offset}")
    @ApiOperation(httpMethod = "POST", value = "返回酒店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "索引位置", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "offset", value = "位移数", required = true, dataType = "long", paramType = "path")
    })
    public Wrapper queryHotelInfo(@PathVariable int index,@PathVariable int offset){
        HotelInfoVo hotelInfoVo = hotelQueryService.getHotelInfo(index,offset);
        return WrapMapper.ok(hotelInfoVo);
    }

}
