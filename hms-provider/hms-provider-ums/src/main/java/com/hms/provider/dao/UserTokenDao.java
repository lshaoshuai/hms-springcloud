package com.hms.provider.dao;

import com.hms.provider.model.domain.UserTokenDo;
import com.hms.provider.model.dto.UmsTokenDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoshao
 * @date 2019/5/30 21:58
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

public interface UserTokenDao {

    @Insert({"insert into user_token_info (phone_num, user_name,user_token) values(#{id}, #{phone_num}, #{user_name}, #{user_token})",
    "ON DUPLICATE KEY UPDATE phone_num = #{phone_num}, user_name = #{user_name}, user_token = #{user_token}"})
    void insertOrUpdateUserInfo(UmsTokenDto user);

    @Select("select * from user_token_info where phone_num = #{mobile}")
    UserTokenDo selectUserInfoByMobile(String mobile);

    @Select("select * from user_token_info where id = #{id}")
    void selectUserInfoById(int id);
}
