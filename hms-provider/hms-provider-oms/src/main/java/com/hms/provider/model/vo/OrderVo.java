package com.hms.provider.model.vo;

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
public class OrderVo implements Serializable {

    private static final long serialVersionUID = 6123862096125274292L;
    private int id;

    private double collection_price;

    private int order_id;

    private String user_id;

    private String room_name;

    private int pay_status;

    private String hotel_name;

    private int room_count;

    private Date order_set_time;

    private Date room_in_time;

    private Date room_out_time;

}
