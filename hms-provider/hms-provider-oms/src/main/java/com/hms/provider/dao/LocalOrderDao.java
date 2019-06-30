package com.hms.provider.dao;

import com.hms.provider.model.domain.LocalOrderDo;
import com.hms.provider.model.dto.DynamicDto;
import com.hms.provider.model.dto.SearchOrderDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/26 19:23
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface LocalOrderDao {

    @Select("select * from local_order_info where order_id = #{orderid}")
    LocalOrderDo queryLocalOrderInfoByOrderId(String orderid);

    @Select("select * from local_order_info limit #{index},#{offset}")
    List<LocalOrderDo> queryLocalOrderInfoByLimit(@Param("index") int index, @Param("offset") int offset);

    @Select("select * from local_order_info where id = #{id}")
    LocalOrderDo queryLocalOrderInfoById(int id);

    @Select("select * from local_order_info where ${type}= #{value} limit #{pageNum},#{pageSize}")
    List<LocalOrderDo> queryLocalOrderInfoByDynamic(SearchOrderDto searchOrderDto);

    @Select("select count(*) from local_order_info where hotel_id = #{hotelid} and ${type}= #{value}")
    int countOrderTotalByDynamic(DynamicDto dynamicDto);

    @Select("select count(*) from local_order_info where hotel_id = #{hotelid}")
    int countOrderTotal(int hotelid);

    @Insert({"insert into local_order_info (id, order_id, user_id, room_id, origin,check_in,check_out,check_in_status,count,hotel_id,money)",
            " values(#{id}, #{order_id}, #{user_id}, #{room_id}, #{origin}, #{check_in}, #{check_out}, #{check_in_status}, #{count}, #{hotel_id}, #{money})",
            "ON DUPLICATE KEY UPDATE order_id = #{order_id}, user_id = #{user_id}, room_id = #{room_id},origin",
            " = #{origin},check_in = #{check_in},check_out = #{check_out},check_in_status = #{check_in_status},count = #{count}, hotel_id = #{hotel_id}, money = #{money}"})
    int insertOrUpdateOrderInfo(LocalOrderDo localOrderDo);

    @Update("update local_order_info set check_in_status = #{status} where order_id = #{orderid}")
    int updateOrderStatus(@Param("status") int status,@Param("orderid") String orderid);

    @Delete("delete from local_order_info where id = #{id}")
    int deleteOrderInfoById(int id);


}
