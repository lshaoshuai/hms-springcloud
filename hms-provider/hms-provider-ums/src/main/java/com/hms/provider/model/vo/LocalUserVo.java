package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/20 23:38
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalUserVo implements Serializable {


    private static final long serialVersionUID = 3403642426407955432L;

    private String usertoken;

    private int role;
}
