package com.hms.dao;

import com.hms.dto.UmsTokenDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luoshao
 * @date 2019/5/30 21:58
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Mapper
public interface UserTokenDao {

    @Insert("insert into user (id, phone_num, user_name,user_token) values(#{id}, #{phone_num}, #{user_name}, #{user_token})")
    public void insertUserinfo(UmsTokenDto user);
}
