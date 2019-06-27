package com.hms.provider.model.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

/**
 * @author luoshao
 * @date 2019/6/1 11:13
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "roominfo")
@Data
@Table(name = "room_info")
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 8050352918615199254L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "room_count")
    private int room_count;

    @Column(name = "room_name")
    private String room_name;

    @Column(name = "room_price")
    private double room_price;

    @Column(name = "room_detail")
    private String room_detail;

    @Column(name = "is_cancel")
    private boolean is_cancel;

    @Column(name = "room_pic")
    private Blob room_pic;

}
