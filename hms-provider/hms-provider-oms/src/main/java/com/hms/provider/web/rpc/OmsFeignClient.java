package com.hms.provider.web.rpc;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.dao.OrderDao;
import com.hms.provider.service.OmsFeignApi;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoshao
 * @date 2019/6/25 22:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RefreshScope
@RestController
@Api(value = "API - OmsFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/local")
public class OmsFeignClient extends BaseController implements OmsFeignApi {

    @Autowired
    OrderDao orderDao;

    @GetMapping("/count")
    @ApiOperation(httpMethod = "GET", value = "获取订单总数")
    @NoNeedAccessAuthentication
    public Wrapper getOrderCount(){
        int count = orderDao.countRoomTotal();
        return WrapMapper.ok(count);
    }

}
