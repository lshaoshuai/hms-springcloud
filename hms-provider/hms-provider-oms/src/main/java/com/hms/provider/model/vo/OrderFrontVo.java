package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luoshao
 * @date 2019/6/25 23:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class OrderFrontVo implements Serializable {

    private static final long serialVersionUID = 5687740245999560895L;

    private long  userId;

    private long username;

    private long phone;

    private long origin;

    private Date checkIn;

    private Date checkOut;

    private int checkInStatus;

    private int roomid;
}
