package com.hms.provider.model.vo;

import lombok.Data;

/**
 * @author luoshao
 * @date 2019/5/31 23:04
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class HotelInfoVo {

    private int id;

    private String hotel_name;

    private String hotel_location;

    private String hotel_pic;

    private String hotel_grade;

    private String neighbor_location;

    private String tap;

    private String commuser_num;

}
