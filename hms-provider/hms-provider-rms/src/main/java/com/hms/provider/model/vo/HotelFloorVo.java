package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/26 18:53
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class HotelFloorVo implements Serializable {

    private static final long serialVersionUID = 5105432732926711590L;

    private int emptyRoom;

    private List<FloorVo> floorVos;

    public HotelFloorVo(int emptyRoom,List<FloorVo> floorVos){

        this.emptyRoom = emptyRoom;

        this.floorVos = new ArrayList<>(floorVos);

    }


}
