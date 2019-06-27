package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 13:52
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "localroominfo")
@Data
@Table(name = "local_room_info")
public class LocalRoomDo implements Serializable {

    private static final long serialVersionUID = 2134242748205059478L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "room_no")
    private int room_no;

    @Column(name = "room_type_id")
    private int room_type_id;

    @Column(name = "room_type")
    private String room_type;

    @Column(name = "room_status")
    private int room_status;

    @Column(name = "floor")
    private int floor;

    @Column(name = "hotel_id")
    private int hotel_id;

    @Column(name = "price")
    private double price;

}
