package com.hms.provider.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author luoshao
 * @date 2019/5/26 11:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@ApiModel(value = "UserTokenVo")
public class UserTokenVo {

    private String phone_num;

    private String acesstoken;
}
