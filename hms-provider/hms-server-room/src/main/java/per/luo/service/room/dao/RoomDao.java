package per.luo.service.room.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import per.luo.service.room.vo.Room;

/**
 * @author luoshao
 * @date 2019/5/13 22:27
 * @projectname HMS
 */

@Mapper
public interface RoomDao {

    @Select("select * from room where id = #{id}")
    public Room getById(@Param("id")long id);


    @Update("update room set is_usage = 2 where id = 1")
    public void update(Room toBeUpdate);
}
