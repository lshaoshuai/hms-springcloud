package com.hms.provider.controller;

import com.hms.core.support.BaseController;
import com.hms.dto.CodeDto;
import com.hms.provider.service.UmsUserService;
import com.hms.vo.UserVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/5/18 19:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/auth")
@Api(description = "用户权限校验接口",value = "WEB - UmsUserLoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //表示标识的这个类是swagger的资源
public class UmsUserLoginController extends BaseController {

    @Resource
    private UmsUserService umsUserService;

    /**
     * 选择有意义的名字，能快速地传达该方法的用途。
     * 参照java驼峰命名法，首字母以小写开头，每个单词首字母大写（第一个单词除外）。
     * @param mobileNo
     * @return
     */
    @PostMapping("/code/{mobileNo}")
    @ApiOperation(httpMethod = "POST", value = "返回验证码")
    @ApiImplicitParam(name = "mobileNo", value = "电话号码", required = true, dataType = "long", paramType = "path")  //表示swagger单独请求参数
    public Wrapper setVerifyCode(@PathVariable Long mobileNo) {

        logger.info("获取用户手机号码. mobile={}" ,mobileNo);
        int varifycoed = umsUserService.createVerifyCode(mobileNo);
        return WrapMapper.ok(varifycoed);
    }

    @PostMapping("/checkcode")
    @ApiOperation(httpMethod = "POST", value = "校验验证码")
    public Wrapper checkVerifyCode(@RequestBody CodeDto codedto) {
        logger.info("获取用户请求验证码TOKEN. token={}" ,codedto);
        String token = umsUserService.createUserToken(codedto);
        return WrapMapper.ok(token);
    }

    /**
     * @RequestBody 前端需要用POST进行提交JSON数据
     * @param verifycode
     * @return
     */
    @PostMapping("/checkcode/{verifycode}")
    @ApiOperation(httpMethod = "POST", value = "校验验证码")
    @ApiImplicitParam(name = "verifycode", value = "验证码", required = true, dataType = "int", paramType = "path")
    public Wrapper checkInteriorLogin(@RequestBody int verifycode) {
        logger.info("获取用户请求验证码. verifycode={}" ,verifycode);
        return WrapMapper.ok();
    }

    @PostMapping("/check/{mobileNo}")
    @ApiOperation(httpMethod = "POST", value = "校验手机号")
    public Wrapper queryUserOrderDetailList(@PathVariable Long mobileNo) {
        logger.info("获取用户手机号码. mobile={}" ,mobileNo);
        UserVo userVo = new UserVo();
        userVo.setPhone_num(mobileNo);
        userVo.setId(1);
        userVo.setUser_name("luo");
        return WrapMapper.ok();
    }

}
