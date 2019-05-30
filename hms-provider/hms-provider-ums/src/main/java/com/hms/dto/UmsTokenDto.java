package com.hms.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author luoshao
 * @date 2019/5/30 22:00
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@ApiModel(value = "UmsTokenDto")
public class UmsTokenDto {

    private long id;

    private long phone_num;

    private String user_name;

    private String user_token;
}
