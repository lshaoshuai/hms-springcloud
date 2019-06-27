package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.dto.LocalRoomDto;
import com.hms.provider.model.dto.SearchRoomDto;
import com.hms.provider.model.dto.StatusRoomDto;
import com.hms.provider.model.vo.EmptyRoomVo;
import com.hms.provider.model.vo.HotelFloorVo;
import com.hms.provider.model.vo.LocalDetailVo;
import com.hms.provider.model.vo.LocalRoomVo;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/24 13:55
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RmsLocalService extends IService {

    HotelFloorVo getHotelFloorInfo(int hotelid);

    List<LocalRoomVo> getLimitRoom(int index, int offset);

    LocalDetailVo getRoomById(int id);

    boolean addRoomInfo(LocalRoomDto localRoomDto);

    List<LocalRoomVo> getRoomByDynamic(SearchRoomDto searchRoomDto);

    boolean removeRoomInfo(int id);

    EmptyRoomVo getEmptyRoomByDynamic(StatusRoomDto statusRoomDto);

    boolean updateRoomInfo(LocalRoomDto localRoomDto);
}
