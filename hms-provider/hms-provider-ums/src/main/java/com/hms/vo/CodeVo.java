package com.hms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author luoshao
 * @date 2019/5/20 10:22
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@ApiModel(value = "CodeVo")
public class CodeVo {

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码 ") //用在出入参数对象的字段上
    private long phone_num;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码 ")
    private int verifyCode;

}
