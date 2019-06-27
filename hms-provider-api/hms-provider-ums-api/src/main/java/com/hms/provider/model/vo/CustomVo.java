package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/27 9:26
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class CustomVo implements Serializable {

    private static final long serialVersionUID = -5386771163555039704L;

    private int id;

    private String user_id;

    private String username;

    private String phone;
}
