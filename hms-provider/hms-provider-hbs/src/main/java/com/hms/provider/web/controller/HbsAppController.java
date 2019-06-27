package com.hms.provider.web.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.model.vo.HotelCountVo;
import com.hms.provider.model.vo.HotelInfoVo;
import com.hms.provider.service.HotelQueryService;
import com.hms.provider.service.OmsFeignApi;
import com.hms.provider.service.RmsFeignApi;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
import java.util.List;

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

    @Resource
    private OmsFeignApi omsFeignApi;

    @Resource
    private RmsFeignApi rmsFeignApi;

    @PostMapping("/info/{index}/{offset}")
    @ApiOperation(httpMethod = "POST", value = "返回酒店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "索引位置", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "offset", value = "位移数", required = true, dataType = "long", paramType = "path")
    })
    @HystrixCommand(fallbackMethod = "httpError")
    public Wrapper queryHotelInfo(@PathVariable int index,@PathVariable int offset){
        List<HotelInfoVo> hotelInfoVos = hotelQueryService.getHotelInfo(index,offset);
        return WrapMapper.ok(hotelInfoVos);
    }

    public Wrapper httpError(int i,int j) {
        return WrapMapper.ok("" + i + j);
    }

    @PostMapping("/change")
    @ApiOperation(httpMethod = "POST", value = "修改酒店信息")
    public Wrapper changHotelInfo(){
        return WrapMapper.ok();
    }

    @PostMapping("/count")
    @ApiOperation(httpMethod = "POST", value = "获取酒店统计数据")
    public Wrapper countTotalInfo(){
        int roomcount = (int)rmsFeignApi.getRoomCount(1).getResult();
        int hotelcount = (int)omsFeignApi.getOrderCount().getResult();
        logger.info("roomcount :{} , ordercount: {}" ,roomcount, hotelcount);
        return WrapMapper.ok(
                new HotelCountVo(roomcount,0,0,hotelcount)
        );
    }

}
