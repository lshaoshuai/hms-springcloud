package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/31 23:04
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class HotelInfoVo implements Serializable {

    private static final long serialVersionUID = 7936069078970255723L;

    private int id;

    private String hotel_name;

    private String hotel_location;

    private String hotel_pic;

    private String hotel_grade;

    private String neighbor_location;

    private String tap;

    private String commuser_num;

    private String hotel_price;

    private boolean favorite;

}
