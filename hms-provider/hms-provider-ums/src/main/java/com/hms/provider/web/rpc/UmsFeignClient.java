package com.hms.provider.web.rpc;

import com.hms.core.support.BaseController;
import com.hms.provider.model.domain.CustomerDo;
import com.hms.provider.model.dto.CustomerDto;
import com.hms.provider.service.UmsFeignApi;
import com.hms.provider.service.UmsLocalUserService;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/6/24 16:14
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RefreshScope
@RestController
@Api(value = "API - UmsFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsFeignClient extends BaseController implements UmsFeignApi {

    @Resource
    UmsLocalUserService umsLocalUserService;

    @Override
    @ApiOperation(httpMethod = "POST", value = "生成客户信息")
    public Wrapper addCustomerInfo(@RequestBody CustomerDto customerDto){

        boolean is_success = umsLocalUserService.addCustomer(customerDto);
        return WrapMapper.ok(is_success);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "查询客户信息")
    public Wrapper queryCustomerInfo(@RequestParam("userid") String userid){
        CustomerDo customerDo = umsLocalUserService.queryCustomer(userid);
        return WrapMapper.ok(customerDo);
    }

}
