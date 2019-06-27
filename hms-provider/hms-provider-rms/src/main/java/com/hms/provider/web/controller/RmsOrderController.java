package com.hms.provider.web.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.service.RmsActionService;
import com.hms.provider.model.vo.RoomVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/16 0:17
 * @projectname HMS
 */

@RestController
@RequestMapping(value = "/room")
@Api(description = "房间信息处理接口",value = "WEB - RmsOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RmsOrderController extends BaseController {

    //动态代理
    @Resource
    private RmsActionService rmsActionService;

    @PostMapping("/detail/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "查询房间详情")
    public Wrapper queryRoomDetailList(@PathVariable int hotelid) {

        logger.info("queryUserOrderDetailList - 查询房间明细. hotelid={}", hotelid);
        Long mobile = getLoginAuthDto().getPhone_num();
        logger.info("操作人信息. mobile={}", mobile);
        List<RoomVo> roomvos = rmsActionService.getRoomsCountInfo(hotelid);
        logger.info("room信息. room[]={}", roomvos);
        return WrapMapper.ok(roomvos);
    }

    @PostMapping("/count/{hotelid}")
    @ApiOperation(httpMethod = "POST", value = "查询房间总数量")
    public Wrapper queryRoomCountList(@PathVariable int hotelid) {

        logger.info("queryUserOrderDetailList - 查询房间明细. hotelid={}", hotelid);
        Long mobile = getLoginAuthDto().getPhone_num();
        logger.info("操作人信息. mobile={}", mobile);
        List<RoomVo> roomRestVos = rmsActionService.getRoomsInfo(hotelid);
        logger.info("roomCount信息. room[]={}", roomRestVos);
        return WrapMapper.ok(roomRestVos);

    }

    @PostMapping("/single/count/{roomid}")
    @ApiOperation(httpMethod = "POST", value = "查询单个房间剩余数量")
    public Wrapper querySingleRoomCount(@PathVariable int roomid) {

        logger.info("queryUserOrderDetailList - 查询房间明细. hotelid={}", roomid);
        Long mobile = getLoginAuthDto().getPhone_num();
        logger.info("操作人信息. mobile={}", mobile);
        int room_count = rmsActionService.getSingleRoomCountInfo(roomid);
        logger.info("roomCount信息:{}", room_count);
        return WrapMapper.ok(room_count);

    }


}
