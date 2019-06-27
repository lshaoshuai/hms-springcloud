package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 13:59
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalRoomVo implements Serializable {
    private static final long serialVersionUID = -1358656996752083029L;

    private int roomId;

    private int roomNum;

    private double roomPrice;

    private int roomStatus;

    private String roomType;

    public LocalRoomVo(){

    }

    public LocalRoomVo(int roomNum,int roomStatus,String roomType){

        this.roomNum = roomNum;

        this.roomStatus = roomStatus;

        this.roomType = roomType;

    }

    public LocalRoomVo(int roomId,int roomNum,int roomStatus,String roomType,double roomPrice){

        this.roomNum = roomNum;

        this.roomStatus = roomStatus;

        this.roomType = roomType;

        this.roomId = roomId;

        this.roomPrice = roomPrice;

    }
}
