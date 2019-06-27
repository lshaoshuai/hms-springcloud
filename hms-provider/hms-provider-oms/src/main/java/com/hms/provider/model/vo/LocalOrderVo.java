package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 17:10
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalOrderVo implements Serializable {

    private static final long serialVersionUID = -437535057133828026L;

    private String orderId;

    private String userId;

    private String username;

    private String roomType;

    private int roomNum;

    private int roomId;

    private String origin;

    private int checkInStatus;

    public LocalOrderVo(String orderId,int roomid,String userId,String username,String roomType,int roomNum,String origin,int checkInStatus){

        this.username = username;
        this.roomId = roomid;
        this.orderId = orderId;
        this.userId = userId;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.origin = origin;
        this.checkInStatus = checkInStatus;

    }
}
