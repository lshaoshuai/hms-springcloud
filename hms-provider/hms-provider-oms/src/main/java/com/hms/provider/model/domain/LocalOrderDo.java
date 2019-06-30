package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author luoshao
 * @date 2019/6/26 17:19
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "localorderinfo")
@Table(name = "local_order_info")
@Data
public class LocalOrderDo implements Serializable {

    private static final long serialVersionUID = 5740691725014339689L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "order_id")
    private String order_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "room_id")
    private int room_id;

    @Column(name = "origin")
    private String origin;

    @Column(name = "check_in")
    private Date check_in;

    @Column(name = "check_out")
    private Date check_out;

    @Column(name = "check_in_status")
    private int check_in_status;

    @Column(name = "count")
    private int count;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "money")
    private int money;

}
