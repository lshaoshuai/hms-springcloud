package com.hms.provider.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.service.RmsOrderService;
import com.hms.vo.RoomVo;
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

/**
 * @author luoshao
 * @date 2019/5/16 0:17
 * @projectname HMS
 */

@RestController
@RequestMapping(value = "/order")
@Api(value = "WEB - RmsOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RmsOrderController extends BaseController {

    //动态代理
    @Resource
    private RmsOrderService rmsOrderService;
    /**
     * 查询订单详情.
     *
     * @param roomNo the order no
     *
     * @return the wrapper
     */
    @PostMapping("roomdetail/{roomNo}")
    @ApiOperation(httpMethod = "POST", value = "查询订单详情")
    public Wrapper queryUserOrderDetailList(@PathVariable String roomNo) {

        logger.info("queryUserOrderDetailList - 查询用户订单明细. orderNo={}", roomNo);

        //Long userId = getLoginAuthDto().getUserId();

        //logger.info("操作人信息. userId={}", userId);

        RoomVo roomvo = rmsOrderService.getRoomDetail(roomNo);
        return WrapMapper.ok(roomvo);
    }
}
