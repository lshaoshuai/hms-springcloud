package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 14:28
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalDetailVo implements Serializable {

    private static final long serialVersionUID = -4625250426838102902L;

    private int roomId;

    private int roomNum;

    private double roomPrice;

    private int roomStatus;

    private String roomType;

    private int roomContain;

    private int roomLocate;

    private int roomAge;

    public LocalDetailVo(){

    }

    public LocalDetailVo(int roomId,int roomNum,int roomStatus,String roomType,double roomPrice,int roomAge,int roomLocate,int roomContain){

        this.roomNum = roomNum;

        this.roomStatus = roomStatus;

        this.roomType = roomType;

        this.roomId = roomId;

        this.roomPrice = roomPrice;

        this.roomLocate =roomLocate;

        this.roomAge = roomAge;

        this.roomContain = roomContain;

    }
}
