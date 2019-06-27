package com.hms.provider.model.dto;

import com.hms.core.annotation.IsMobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/26 11:17
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

/***
 * <p> DTO（Data Transfer Object）：数据传输对象，这个概念来源于J2EE的设计模式，
 * 原来的目的是为了EJB的分布式应用提供粗粒度的数据实体，以减少分布式调用的次
 * 数，从而提高分布式调用的性能和降低网络负载，但在这里，我泛指用于展示层与服
 * 务层之间的数据传输对象。</p>
 *
 */

@Data
@ApiModel(value = "CodeVo")
public class CodeDto implements Serializable {

    private static final long serialVersionUID = 6836040656751003424L;
    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码 ") //用在出入参数对象的字段上
    @IsMobile
    private String phone_num;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码 ")
    private int verifyCode;

}