package com.hms.provider.dao;

import com.hms.provider.model.dto.UmsTokenDto;
import org.apache.ibatis.annotations.Insert;

/**
 * @author luoshao
 * @date 2019/5/30 21:58
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

public interface UserTokenDao {

    @Insert({"insert into user_info (id, phone_num, user_name,user_token) values(#{id}, #{phone_num}, #{user_name}, #{user_token})",
    "ON DUPLICATE KEY UPDATE id = #{id}, phone_num = #{phone_num}, user_name = #{user_name}, user_token = #{user_token}"})
    void insertOrUpdateUserinfo(UmsTokenDto user);
}
