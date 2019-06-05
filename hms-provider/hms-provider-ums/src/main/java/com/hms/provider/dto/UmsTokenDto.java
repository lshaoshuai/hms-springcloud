package com.hms.provider.dto;

import com.hms.core.annotation.IsMobile;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/30 22:00
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@ApiModel(value = "UmsTokenDto")
public class UmsTokenDto implements Serializable {

    private static final long serialVersionUID = 4341237600124353997L;

    private int id;

    @IsMobile
    private String phone_num;

    private String user_name;

    private String user_token;
}
