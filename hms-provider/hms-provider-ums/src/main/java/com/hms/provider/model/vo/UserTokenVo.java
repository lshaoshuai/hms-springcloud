package com.hms.provider.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/26 11:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@ApiModel(value = "UserTokenVo")
public class UserTokenVo implements Serializable {

    private static final long serialVersionUID = -549712177817934136L;

    private long id;

    private String phone_num;

    private String user_name;

    private String user_id;

    private String email;

    private String accesstoken;
}
