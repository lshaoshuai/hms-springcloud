package com.hms.provider.dao;

import com.hms.provider.model.domain.HotelInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/31 23:07
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface HotelInfoDao {

    @Select("select * from hotel_info limit #{index},#{offset}")
    List<HotelInfo> queryLimitHotelInfo(@Param("index") int index, @Param("offset") int offset);

    @Select("select floor from hotel_info where id = #{hotelid}")
    int queryHotelFloor(int hotelid);
}
