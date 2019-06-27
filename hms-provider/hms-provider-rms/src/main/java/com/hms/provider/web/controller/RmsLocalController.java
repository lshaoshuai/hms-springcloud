package com.hms.provider.web.controller;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.model.dto.LocalRoomDto;
import com.hms.provider.model.dto.SearchRoomDto;
import com.hms.provider.model.dto.StatusRoomDto;
import com.hms.provider.model.vo.*;
import com.hms.provider.service.RmsFeignApi;
import com.hms.provider.service.RmsLocalService;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/24 13:56
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/local")
@Api(description = "本地房间信息处理接口",value = "WEB - RmsOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RmsLocalController extends BaseController {


    @Resource
    RmsLocalService rmsLocalService;

    @Resource
    RmsFeignApi rmsFeignApi;

    @PostMapping("/detail/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "查询酒店所有楼层的房间信息")
    @NoNeedAccessAuthentication
    public Wrapper queryRoomDetailList(@PathVariable int hotelid) {

        logger.info("获取到酒店ID = {}", hotelid);
        HotelFloorVo hotelFloorVo = rmsLocalService.getHotelFloorInfo(hotelid);
        return WrapMapper.ok(hotelFloorVo);
    }

    @PostMapping("/limit/{pagenum}/{pagesize}")
    @ApiOperation(httpMethod = "POST", value = "查询酒店所有房间信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pagenum", value = "索引位置", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pagesize", value = "位移数", required = true, dataType = "int", paramType = "path")
    })
    @NoNeedAccessAuthentication
    public Wrapper queryLoaclRoomList(@PathVariable int pagenum,@PathVariable int pagesize) {

        logger.info("pagenum: {},pagesize: {}", pagenum,pagesize);
        List<LocalRoomVo> localRoomVos = rmsLocalService.getLimitRoom(pagenum,pagesize);
        return WrapMapper.ok(new LimitRoomVo(pagenum,pagesize,(int)rmsFeignApi.getRoomCount(1).getResult(),localRoomVos));
    }

    @PostMapping("/room/detail/{id}")
    @ApiOperation(httpMethod = "POST", value = "通过房间ID查询房间信息")
    @NoNeedAccessAuthentication
    public Wrapper queryRoomInfo(@PathVariable int id) {

        logger.info("获取到房间ID = {}", id);
        LocalDetailVo localDetailVo = rmsLocalService.getRoomById(id);
        return WrapMapper.ok(localDetailVo);
    }

    @PostMapping("/add")
    @ApiOperation(httpMethod = "POST", value = "添加或更新房间信息房间信息")
    @NoNeedAccessAuthentication
    public Wrapper addLoacalRoomInfo(@RequestBody LocalRoomDto localRoomDto) {

        logger.info("获取到房间信息 = {}", localRoomDto);
        boolean is_success = rmsLocalService.addRoomInfo(localRoomDto);
        if(is_success){
            return WrapMapper.ok("添加或更新成功");
        }else {
            return WrapMapper.ok("添加或更新成功失败");
        }

    }

    @PostMapping("/list")
    @ApiOperation(httpMethod = "POST", value = "动态查询客房列表")
    @NoNeedAccessAuthentication
    public Wrapper searchRoomList(@RequestBody SearchRoomDto searchRoomDto) {
        logger.info("获取到查询请求信息 = {}", searchRoomDto);
        List<LocalRoomVo>  localRoomVos = rmsLocalService.getRoomByDynamic(searchRoomDto);
        return WrapMapper.ok(new LimitRoomVo(searchRoomDto.getPageNum(),searchRoomDto.getPageSize(),(int)rmsFeignApi.getRoomCount(1).getResult(),localRoomVos));
    }

    @PostMapping("/remove/{id}")
    @ApiOperation(httpMethod = "POST", value = "根据房间ID删除客服信息")
    @NoNeedAccessAuthentication
    public Wrapper removeLoacalRoomInfo(@PathVariable int id) {

        logger.info("获取到房间ID = {}", id);
        boolean is_success = rmsLocalService.removeRoomInfo(id);
        if(is_success){
            return WrapMapper.ok("删除成功");
        }else {
            return WrapMapper.ok("删除失败");
        }

    }

    @PostMapping("/empty/list")
    @ApiOperation(httpMethod = "POST", value = "动态查询客房列表")
    @NoNeedAccessAuthentication
    public Wrapper searchEmptyRoomList(@RequestBody StatusRoomDto statusRoomDto) {
        logger.info("获取到查询请求信息 = {}", statusRoomDto);
        EmptyRoomVo emptyRoomVo  = rmsLocalService.getEmptyRoomByDynamic(statusRoomDto);
        return WrapMapper.ok(emptyRoomVo);
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "更新客房信息")
    @NoNeedAccessAuthentication
    public Wrapper updateLocalRoomInfo(@RequestBody LocalRoomDto localRoomDto) {
        logger.info("获取到更新请求信息 = {}", localRoomDto);
        boolean is_success  = rmsLocalService.updateRoomInfo(localRoomDto);
        if(is_success){
            return WrapMapper.ok("添加或更新成功");
        }else {
            return WrapMapper.ok("添加或更新成功失败");
        }

    }
}
