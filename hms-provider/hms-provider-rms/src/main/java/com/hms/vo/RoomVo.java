package com.hms.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "RoomVo")
public class RoomVo implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    int id;

    int room_num;

    double room_money;

    String room_type;

    int rest_room_count;

    int is_usage;

}
