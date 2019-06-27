package com.hms.provider.web.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.model.dto.LocalUserDto;
import com.hms.provider.service.UmsLocalUserService;
import com.hms.provider.model.vo.LocalUserVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/6/24 11:32
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/localuser")
@Api(description = "本地管理员权限校验接口",value = "WEB - UmsLocalUserLoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //表示标识的这个类是swagger的资源
@Validated
public class UmsLocalUserLoginController extends BaseController {


    @Resource
    UmsLocalUserService umsLocalUserService;

    @PostMapping("/login")
    @ApiOperation(httpMethod = "POST", value = "用户登陆") //swagger的api操作
    public Wrapper userLogin(@RequestBody LocalUserDto localUserDto){
        logger.info("获取前端请求信息. token={}" ,localUserDto);
        LocalUserVo localUserVo = umsLocalUserService.checkLocalUserInfo(localUserDto);
        return WrapMapper.ok(localUserVo);
    }

    @PostMapping("/register")
    @ApiOperation(httpMethod = "POST", value = "用户注册") //swagger的api操作
    public Wrapper userRegister(){
        logger.info("获取前端请求信息. token={}");
        return WrapMapper.ok();
    }

}
