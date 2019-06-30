package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author luoshao
 * @date 2019/6/1 23:13
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 4574687823191927531L;

    private double collection_price;

    private String user_id;

    private String room_name;

    private int room_id;

    private int  hotel_id;

    private String hotel_name;

    private int room_count;

    private Date room_in_time;

    private Date room_out_time;
}
