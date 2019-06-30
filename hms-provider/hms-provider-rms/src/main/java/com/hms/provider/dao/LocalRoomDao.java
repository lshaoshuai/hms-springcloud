package com.hms.provider.dao;

import com.hms.provider.model.domain.LocalRoomDo;
import com.hms.provider.model.dto.SearchRoomDto;
import com.hms.provider.model.dto.StatusRoomDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/24 14:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface LocalRoomDao {

    @Select("select * from local_room_info where hotel_id = #{hotel_id}")
    List<LocalRoomDo> queryLocalRoomInfo(int hotel_id);

    @Select("select * from local_room_info limit #{index},#{offset}")
    List<LocalRoomDo> queryLocalRoomInfoByLimit(@Param("index") int index,@Param("offset") int offset);

    @Select("select * from local_room_info where id = #{id}")
    LocalRoomDo queryLocalRoomInfoById(int id);

    @Select("select * from local_room_info where ${type}= #{value} limit #{pageNum},#{pageSize}")
    List<LocalRoomDo> queryLocalRoomInfoByDynamic(SearchRoomDto searchRoomDto);

    @Select("select count(*) from local_room_info where hotel_id = #{hotelid}")
    int countRoomTotal(int hotelid);

    @Insert({"insert into local_room_info (id, floor, hotel_id,room_no,room_status,room_type_id,room_type,price)",
            " values(#{id}, #{floor}, #{hotel_id}, #{room_no}, #{room_status}, #{room_type_id}, #{room_type}, #{price})",
            "ON DUPLICATE KEY UPDATE floor = #{floor}, hotel_id = #{hotel_id}, room_no = #{room_no},room_status",
            " = #{room_status},room_type_id = #{room_type_id},room_type = #{room_type},price = #{price}"})
    int insertOrUpdateRoomInfo(LocalRoomDo localRoomDo);

    @Delete("delete from local_room_info where id = #{id}")
    int deleteRoomInfoById(int id);

    @Select("select * from local_room_info where ${type}= #{value} and room_status = #{status} limit #{pageNum},#{pageSize}")
    List<LocalRoomDo> queryLocalEmptyRoomByDynamic(StatusRoomDto statusRoomDto);

    @Select("select count(*) from local_room_info where ${type}= #{value}")
    int queryDynamicRoomTotal(@Param("type") String type,@Param("value") String value);

    @Select("select count(*) from local_room_info where hotel_id = #{hotelid} and ${type}= #{value} and room_status = #{status}")
    int countDynamicRoomTotal(@Param("hotelid") int hotelid,@Param("type") String type,@Param("value") String value,@Param("status") int status);

    @Update("update local_room_info set floor = #{floor}, hotel_id = #{hotel_id}, room_no = #{room_no},room_status " +
            "= #{room_status},room_type_id = #{room_type_id},room_type = #{room_type},price = #{price} where id = #{id}")
    int updateLocalRoomInfoById(LocalRoomDo localRoomDo);

    @Update("update local_room_info set room_status = #{status} where id = #{id}")
    int updateLocalRoomInfoStatusById(@Param("status") int status,@Param("id") int id);

    @Select("select price from local_room_info where id = #{id}")
    int queryRoomPrice(int id);

    @Select("SELECT id FROM local_room_info t1 JOIN (SELECT RAND() * (SELECT MAX(id) FROM local_room_info WHERE " +
            "room_status = 5 and room_type = #{type}) AS nid) t2 ON t1.id > t2.nid LIMIT 1")
    int queryRandomRoomByType(String type);

//    class DynamicRoomInfoDaoProvider {
//        public String findRoomList(@Param("type") String type,@Param("value") String value) {
//            String sql = "SELECT * FROM local_room_info where ${type} = #{value}";
//            return sql;
//        }
//    }
}
