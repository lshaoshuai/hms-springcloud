package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.vo.RoomVo;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/4 10:23
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RmsActionService extends IService {

    List<RoomVo> getRoomsInfo(int hotel_id);

    List<RoomVo> getRoomsCountInfo(int hotel_id);

    int getSingleRoomCountInfo(int room_id);

    List<String> roomTypeList(int hotelid);
}
