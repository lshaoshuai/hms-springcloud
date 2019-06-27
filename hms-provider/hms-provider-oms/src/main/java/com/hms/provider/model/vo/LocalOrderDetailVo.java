package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author luoshao
 * @date 2019/6/26 17:15
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalOrderDetailVo implements Serializable {

    private static final long serialVersionUID = -6683582676016604834L;

    private String orderId;

    private String userId;

    private String username;

    private String phone;

    private int roomId;

    private String roomType;

    private int roomNum;

    private double price;

    private String origin;

    private Date checkIn;

    private Date checkOut;

    private int checkInStatus;

    private double money;

    public LocalOrderDetailVo(String orderId, String userId, String username, String phone, int roomId, String roomType, int roomNum, double price, String origin, Date checkIn, Date checkOut, int checkInStatus, double money) {
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomNum = roomNum;
        this.price = price;
        this.origin = origin;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkInStatus = checkInStatus;
        this.money = money;
    }
}
