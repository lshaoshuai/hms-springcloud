package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 19:14
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class CheckDto implements Serializable {

    private static final long serialVersionUID = -1336944460793141149L;

    private String orderId;

    private int checkInStatus;

    private int roomId;

    private int roomStatus;

}
