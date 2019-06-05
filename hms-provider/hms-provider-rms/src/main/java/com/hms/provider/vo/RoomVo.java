package com.hms.provider.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "RoomVo")
public class RoomVo implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    private int id;

    private int room_count;

    private String room_name;

    private double room_price;

    private String room_detail;

    private boolean is_cancel;

    private byte[] room_pic;


}
