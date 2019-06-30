package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luoshao
 * @date 2019/6/26 21:51
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class OrderFrontDto implements Serializable {

    private static final long serialVersionUID = 4251493192524844799L;

    private String userId;

    private String username;

    private String phone;

    private String  origin;

    private Date checkIn;

    private Date checkOut;

    private int checkInStatus;

    private int roomId;

}
