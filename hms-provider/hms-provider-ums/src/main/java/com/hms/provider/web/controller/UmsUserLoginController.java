package com.hms.provider.web.controller;

import com.hms.core.annotation.IsMobile;
import com.hms.core.support.BaseController;
import com.hms.provider.model.dto.CodeDto;
import com.hms.provider.service.UmsUserService;
import com.hms.provider.model.vo.UserTokenVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author luoshao
 * @date 2019/5/18 19:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

/***
 * controller层常用注解
 * <p> @Controller
 * //@Controller注解标注类的方法，return时会被视图处理器识别成静态文件的路径。默认为templates文件夹下。
 * 如return "test/hello"表示的是默认路径下的test文件夹中的名叫hello的文件，带上后缀名.html或btl等也可以识别。
 *
 * //@ResponseBody
 * //@ResponseBody可以标注方法也可以标注类，当标注方法时表示该方法的返回值会被解析成json（字符串会不会被转换）
 * ，直接写入HTTP Response Body中，视图处理器将不会将return的参数识别成路径。当它标注类时，类中所有方法的返回值都
 * 将直接返回值到页面，相当于给所有的类都加上@ResponseBody注解。
 *
 * //@RestController
 * //@RestController是@Controller和@ResponseBody的结合体，只能注解类，return返回的值将被转换成json，字符
 * 串除外，直接写入HTTP相应体返回到页面中。
 *
 * //@RequestMapping
 * 它可以注解类也可以注解方法，注解类时标注请求的路径，标注方法时表示将特定的URL映射到指定的方法。
 *
 * //@GetMapping 相等于@RequestMapping(value = "",method = RequestMethod.GET)
 * //@PostMapping
 * //@PutMapping
 *
 * //@PathVariable：用于获取url中的参数,{}中的变量名与方法中的形参名一致 若是不一致则写成@PathVariable(""),""中要与{}
 * 中的变量名一致
 *
 * //@RequestParam
 * //@RequestParam，用来处理Content-Type为application/x-www-form-urlencoded（默认类型如果不指定），使用
 * value属性可以指定获取参数的key。
 *
 * //@RequestBody
 * //@RequestBody 注解一般用来处理非Content-Type: application/x-www-form-urlencoded编码格式的数据，在GET
 * 请求中没有请求体所以一般不适用，在post请求中必须指定Content-Type后才能使用它，如ajax请求指定发送格式为application/json。
 *
 * 在Spring MVC应用中，允许使用上述各类的valid注解去验证@RequestParam和@PathVariable中定义的各类params。
 * 使用@Validated来修饰整个类，表示其中方法需要在method这个级别使用validation逻辑进行验证。
 * 针对@PathVariable/@RequestParam直接挂接注解的方式来修饰特定变量。
 * </p>
 *
 **/
@RestController
@RequestMapping(value = "/auth")
@Api(description = "用户权限校验接口",value = "WEB - UmsUserLoginController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE) //表示标识的这个类是swagger的资源
@Validated
public class UmsUserLoginController extends BaseController {

    @Resource
    private UmsUserService umsUserService;

    @IsMobile
    String mobileNo;
    /**
     * 选择有意义的名字，能快速地传达该方法的用途。
     * 参照java驼峰命名法，首字母以小写开头，每个单词首字母大写（第一个单词除外）。
     * @param mobileNo
     * @return
     */
    @PostMapping("/code/{mobileNo}")
    @ApiOperation(httpMethod = "POST", value = "返回验证码") //swagger的api操作
    @ApiImplicitParam(name = "mobileNo", value = "电话号码", required = true, dataType = "long", paramType = "path")  //表示swagger单独请求参数
    public Wrapper setVerifyCode(@PathVariable("mobileNo") @IsMobile String mobileNo) {
        //校验简单参数类型(String)应该在类上加@Validated
        logger.info("获取用户手机号码. mobile={}" ,mobileNo);
        int varifycoed = umsUserService.createVerifyCode(mobileNo);
        return WrapMapper.ok(varifycoed);
    }

    @PostMapping("/checkcode")
    @ApiOperation(httpMethod = "POST", value = "校验验证码")
    public Wrapper checkVerifyCode(@RequestBody @Valid CodeDto codedto) {
        logger.info("获取用户请求验证码TOKEN. token={}" ,codedto);
        UserTokenVo userTokenVo = umsUserService.createUserToken(codedto);
        return WrapMapper.ok(userTokenVo);
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
        return WrapMapper.ok();
    }

}
