package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

/**
 * @author luoshao
 * @date 2019/5/31 13:22
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "hotelinfo")
@Table(name = "hotel_info") //映射的表名称
@Data
public class HotelInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "hotel_name")
    private String hotel_name;

    @Column(name = "hotel_location")
    private String hotel_location;

    @Column(name = "hotel_pic")
    private Blob hotel_pic;

    @Column(name = "hotel_grade")
    private String hotel_grade;

    @Column(name = "neighbor_location")
    private String neighbor_location;

    @Column(name = "tap")
    private String tap;

    @Column(name = "floor")
    private int floor;

    @Column(name = "location")
    private String location;

    @Column(name = "commuser_num")
    private String commuser_num;

    @Column(name = "hotel_price")
    private String hotel_price;

    @Column(name = "city")
    private String city;

    @Column(name = "favorite")
    private boolean favorite;

}
