package com.hms.provider.web.controller;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.model.dto.SearchHotelDto;
import com.hms.provider.model.vo.HotelCountVo;
import com.hms.provider.model.vo.HotelInfoVo;
import com.hms.provider.service.HotelQueryService;
import com.hms.provider.service.ImsFeignApi;
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
import org.springframework.web.bind.annotation.*;

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

    @Resource
    private ImsFeignApi imsFeignApi;

    @PostMapping("/info/{index}/{offset}")
    @ApiOperation(httpMethod = "POST", value = "返回酒店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "index", value = "索引位置", required = true, dataType = "long", paramType = "path"),
            @ApiImplicitParam(name = "offset", value = "位移数", required = true, dataType = "long", paramType = "path")
    })
    @HystrixCommand(fallbackMethod = "httpError")
    @NoNeedAccessAuthentication
    public Wrapper queryHotelInfoList(@PathVariable int index,@PathVariable int offset){
        List<HotelInfoVo> hotelInfoVos = hotelQueryService.getHotelInfoList(index,offset);
        return WrapMapper.ok(hotelInfoVos);
    }

    public Wrapper httpError(int i,int j) {
        return WrapMapper.ok("" + i + j);
    }

    @PostMapping("/info/dynamic")
    @ApiOperation(httpMethod = "POST", value = "修改酒店信息")
    @HystrixCommand(fallbackMethod = "httpError")
    @NoNeedAccessAuthentication
    public Wrapper queryDynamicHotelInfo(@RequestBody SearchHotelDto searchHotelDto){
        List<HotelInfoVo> hotelInfoVos = hotelQueryService.getHotelByDynamic(searchHotelDto);
        return WrapMapper.ok(hotelInfoVos);
    }

    @PostMapping("/count")
    @ApiOperation(httpMethod = "POST", value = "获取酒店统计数据")
    @NoNeedAccessAuthentication
    public Wrapper countTotalInfo(){
        int roomcount = (int)rmsFeignApi.getEmptyRoomCount(1,5,"hotel_id","1").getResult();
        int hotelcount = (int)omsFeignApi.getOrderCount().getResult();
        int stockcount =  (int)imsFeignApi.GetStockCount().getResult();
        logger.info("roomcount :{} , ordercount: {}, stockcount: {}" ,roomcount, hotelcount,stockcount);
        return WrapMapper.ok(
                new HotelCountVo(roomcount,0,stockcount,hotelcount)
        );
    }

    @PostMapping("/single/info/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "返回酒店信息")
    @NoNeedAccessAuthentication
    public Wrapper queryHotelInfo(@PathVariable int hotelid){
        HotelInfoVo hotelInfoVo = hotelQueryService.getHotelInfo(hotelid);
        return WrapMapper.ok(hotelInfoVo);
    }


}
