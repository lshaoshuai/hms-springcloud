package com.hms.provider.web.rpc;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.dao.LocalOrderDao;
import com.hms.provider.dao.OrderDao;
import com.hms.provider.model.dto.OrderFrontDto;
import com.hms.provider.service.OmsFeignApi;
import com.hms.provider.service.OmsLocalService;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/6/25 22:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RefreshScope
@RestController
@Api(value = "API - OmsFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsFeignClient extends BaseController implements OmsFeignApi {

    @Autowired
    OrderDao orderDao;

    @Autowired
    LocalOrderDao localOrderDao;

    @Resource
    OmsLocalService omsLocalService;

    @ApiOperation(httpMethod = "GET", value = "获取订单总数")
    @NoNeedAccessAuthentication
    public Wrapper getOrderCount(){
        int count = localOrderDao.countOrderTotal(1);
        return WrapMapper.ok(count);
    }

    @ApiOperation(httpMethod = "POST", value = "向酒店提交订单")
    @NoNeedAccessAuthentication
    public Wrapper commitLocalOrder(@RequestBody OrderFrontDto orderFrontDto){
        logger.info("获取到信息： {}",orderFrontDto);
        boolean is_success = omsLocalService.frontCheckIn(orderFrontDto);
        return WrapMapper.ok(is_success);
    }
}
