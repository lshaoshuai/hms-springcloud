package com.hms.provider.dao;

import com.hms.provider.domain.HotelInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoshao
 * @date 2019/5/31 23:07
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface HotelInfoDao {

    @Select("select * from hotel_info limit #{index},#{offset}")
    HotelInfo queryLimitHotelInfo(@Param("index") int index,@Param("offset") int offset);
}
