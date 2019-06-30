package com.hms.provider.dao;

import com.hms.provider.model.domain.CustomerDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoshao
 * @date 2019/6/26 22:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface CustomDao {

    @Insert({"insert into customer_info (phone,user_id,username) values (#{phone},#{user_id},#{username})",
    "ON DUPLICATE KEY UPDATE phone = #{phone}, username = #{username}, user_id = #{user_id}"})
    int insertCustomerInfo(CustomerDo customerDo);

    @Select("select * from customer_info where user_id = #{userid}")
    CustomerDo selectCustomInfo(String userid);
}
