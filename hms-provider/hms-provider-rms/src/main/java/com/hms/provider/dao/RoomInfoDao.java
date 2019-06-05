package com.hms.provider.dao;

import com.hms.provider.domain.RoomInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/1 11:36
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RoomInfoDao {

    @Select("select * from room_info where hotel_id = #{hotel_id}")
    List<RoomInfo> queryRoomInfo(@Param("hotel_id") int hotel_id);
}
