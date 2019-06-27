package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/24 14:01
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class FloorVo implements Serializable {


    private static final long serialVersionUID = 6640649127934853774L;

    private int floor;

    private List<LocalRoomVo> roomlist;

    public FloorVo(){}

    public FloorVo(int floor,List<LocalRoomVo> roomlist){
        this.floor = floor;
        this.roomlist = new ArrayList<>(roomlist);
    }
 }
