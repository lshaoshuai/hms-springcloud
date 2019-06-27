package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 13:15
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LocalRoomDto implements Serializable {

    private static final long serialVersionUID = -2505026404365591536L;

    private  int roomId;

    private  int roomNum;

    private  String roomType;

    private  double roomPrice;

    private  int roomStatus;

    private  int roomContain;

    private  int roomLocate;

}
