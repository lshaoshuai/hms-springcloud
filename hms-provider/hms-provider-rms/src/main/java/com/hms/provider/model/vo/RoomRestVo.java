package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/8 10:02
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class RoomRestVo implements Serializable {

    private static final long serialVersionUID = 7084716190988134871L;

    private int id;

    private int room_count;

    public RoomRestVo(int id,int room_count){

        this.id = id;

        this.room_count = room_count;
    }

}
