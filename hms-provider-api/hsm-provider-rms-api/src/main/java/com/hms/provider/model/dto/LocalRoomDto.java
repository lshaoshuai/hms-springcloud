package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 13:52
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalRoomDto implements Serializable {

    private static final long serialVersionUID = -3539844357032519169L;

    private int id;

    private int room_no;

    private int room_type_id;

    private String room_type;

    private int room_status;

    private int floor;

    private int hotel_id;

    private double price;

}
