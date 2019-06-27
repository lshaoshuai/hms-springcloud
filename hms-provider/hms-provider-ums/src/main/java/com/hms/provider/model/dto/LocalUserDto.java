package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 12:09
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalUserDto implements Serializable {

    private static final long serialVersionUID = -4514578777985865597L;

    private String username;

    private String password;

    private int hotelid;
}
