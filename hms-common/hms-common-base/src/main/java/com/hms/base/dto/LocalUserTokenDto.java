package com.hms.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 15:37
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalUserTokenDto implements Serializable {

    private static final long serialVersionUID = -9113319537517618646L;

    private long id;

    private String username;

    private int role;

    private int hotelid;

    private String usertoken;
}
