package com.hms.provider.dao;

import com.hms.provider.vo.RoomVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author luoshao
 * @date 2019/5/16 10:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Mapper
public interface RoomDao {

    @Select("select * from room where id = #{id}")
    public RoomVo getById(@Param("id")String id);


    @Update("update room set is_usage = 2 where id = 1")
    public void update(RoomVo toBeUpdate);
}
