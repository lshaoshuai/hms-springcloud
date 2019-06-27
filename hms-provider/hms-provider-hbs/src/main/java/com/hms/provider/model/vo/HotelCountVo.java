package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/25 22:42
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class HotelCountVo implements Serializable {

    private static final long serialVersionUID = -3412155418197228059L;

    private int roomCount;

    private int userCount;

    private int productCount;

    private int orderCount;

    public HotelCountVo(int roomCount,int userCount,int productCount,int orderCount){

        this.roomCount = roomCount;

        this.userCount = userCount;

        this.productCount = productCount;

        this.orderCount = orderCount;

    }

}
