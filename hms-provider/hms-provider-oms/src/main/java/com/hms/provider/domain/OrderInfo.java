package com.hms.provider.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author luoshao
 * @date 2019/6/1 15:02
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "orderinfo")
@Table(name = "order_info")
@Data
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = -2106909413775469731L;
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "collection_price")
    private double collection_price;

    @Column(name = "order_id")
    private String order_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "room_name")
    private String room_name;

    @Column(name = "pay_status")
    private int pay_status;

    @Column(name = "hotel_name")
    private String hotel_name;

    @Column(name = "room_count")
    private int room_count;

    @Column(name = "order_set_time")
    private Date order_set_time;

    @Column(name = "room_in_time")
    private Date room_in_time;

    @Column(name = "room_out_time")
    private Date room_out_time;

    @Column(name = "room_id")
    private int room_id;
}
