package com.hms.provider.web.controller;

import com.hms.base.dto.UserTokenDto;
import com.hms.core.support.BaseController;
import com.hms.provider.dto.OrderDto;
import com.hms.provider.service.OmsOrderService;
import com.hms.provider.vo.OrderVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/commit/{hotelid}/{roomid}")
    @ApiOperation(httpMethod = "POST", value = "支付提交订单")
    public Wrapper commitOrderForm(@PathVariable int hotelid,@PathVariable int roomid) {

        UserTokenDto userTokenDto = getLoginAuthDto();
        logger.info("roomid:{},userToken:{} ",hotelid,userTokenDto);
        boolean is_success = omsOrderService.commitOrder(hotelid,roomid,userTokenDto.getPhone_num());
        return WrapMapper.ok(is_success);
    }

    @PostMapping("/detail/{userid}")
    @ApiOperation(httpMethod = "POST", value = "根据用户ID查询订单信息")
    public Wrapper queryOrderDetailList(@PathVariable String userid) {

        UserTokenDto userTokenDto = getLoginAuthDto();
        logger.info("userid:{},userToken:{}",userid,userTokenDto);
        OrderVo[] orderVos = omsOrderService.queryOrderInfo(userid);
        //CorrelationData消息的唯一id
        return WrapMapper.ok(orderVos);
    }

    @PostMapping("/create/body")
    @ApiOperation(httpMethod = "POST", value = "生成订单信息")
    public Wrapper createOrderInfo(@RequestBody OrderDto orderDto) throws Exception {

        UserTokenDto userTokenDto = getLoginAuthDto();
        logger.info("userToken:{}",userTokenDto);
        logger.info("获取到订单JSON数据{}",orderDto);
        String is_success = omsOrderService.createOrderInfo(orderDto);
        if(is_success.isEmpty()){
            return WrapMapper.ok("fail");
        }else {
            return WrapMapper.ok(is_success);
        }

    }

    @PostMapping("/select/{id}")
    @ApiOperation(httpMethod = "POST", value = "生成订单信息")
    public Wrapper selectOrderInfo(){

        logger.info("测试JRebel");
        return WrapMapper.ok();
    }

}
