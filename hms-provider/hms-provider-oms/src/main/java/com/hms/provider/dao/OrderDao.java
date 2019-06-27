package com.hms.provider.dao;

import com.hms.provider.model.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/16 10:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface OrderDao {

    @Select("select * from order_info where user_id = #{user_id}")
    List<OrderInfo> queryFromOrderInfo(@Param("user_id") String user_id);

    @Insert("insert into order_info(id,collection_price,hotel_name,order_id,order_set_time,pay_status,room_count,room_in_time,room_name" +
            ",room_out_time,user_id,room_id) values(#{id},#{collection_price},#{hotel_name},#{order_id},#{order_set_time},#{pay_status}," +
            "#{room_count},#{room_in_time},#{room_name},#{room_out_time},#{user_id},#{room_id})")
    void insertOrderInfo(OrderInfo orderInfo);

    @Update("update order_info set pay_status = #{pay_status} where order_id = #{order_id}")
    void updateOrderInfo(@Param("pay_status") int pay_status,@Param("order_id") int order_id);

    @Select("select * from order_info where order_id = #{order_id}")
    OrderInfo queryByOrderID(@Param("order_id") String order_id);

    @Select("select count(*) from order_info")
    int countRoomTotal();


}
