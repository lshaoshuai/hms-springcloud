package com.hms.provider.domain;

import lombok.Data;

import javax.persistence.*;
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
public class OrderInfo {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "order_id")
    private int order_id;

    @Column(name = "room_id")
    private int room_id;

    @Column(name = "pay_status")
    private boolean pay_status;

    @Column(name = "collection_price")
    private double collection_price;

    @Column(name = "create_time")
    private Date create_time;

    @Column(name = "expire_time")
    private Date expire_time;
}
