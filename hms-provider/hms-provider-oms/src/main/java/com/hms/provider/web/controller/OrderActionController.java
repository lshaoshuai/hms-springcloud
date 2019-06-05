package com.hms.provider.web.controller;

import com.hms.base.dto.UserTokenDto;
import com.hms.core.support.BaseController;
import com.hms.provider.service.OmsOrderService;
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
@Api(description = "订单信息处理接口",value = "WEB - OmsOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OrderActionController extends BaseController {


    @Resource
    private OmsOrderService omsOrderService;

    @PostMapping("/detail/{roomid}")
    @ApiOperation(httpMethod = "POST", value = "查询订单详情")
    public Wrapper queryUserOrderDetailList(@PathVariable int roomid) {

        UserTokenDto userTokenDto = getLoginAuthDto();
        logger.info("userToken:{}",userTokenDto);
        omsOrderService.sendOrderInfoToMq(roomid,userTokenDto.getPhone_num());
        //CorrelationData消息的唯一id
        return WrapMapper.ok();
    }
}
