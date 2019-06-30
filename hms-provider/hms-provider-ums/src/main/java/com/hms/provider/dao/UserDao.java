package com.hms.provider.dao;

import com.hms.provider.model.domain.UserDo;
import com.hms.provider.model.vo.UserVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author luoshao
 * @date 2019/5/16 10:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

public interface UserDao {


    @Select("select * from user where phone_num = #{phone_num}")
    UserVo getByPhonenum(@Param("phone_num")String phone_num);


    @Update("update room set is_usage = 2 where id = 1")
    void update(UserVo toBeUpdate);

    @Insert({"insert into user_info (id, phone_num, user_name,user_id,email) values(#{id}, #{phone_num}, #{user_name}, #{user_id}, #{email})",
            "ON DUPLICATE KEY UPDATE  phone_num = #{phone_num}, user_name = #{user_name}, user_id = #{user_id}, email = #{email}"})
    int insertOrUpdateUserinfo(UserDo userDo);

    @Select("select * from user_info where phone_num = #{mobile}")
    UserDo selectUserInfoByMobile(String mobile);

    @Select("select * from user_info where id = #{id}")
    UserDo selectUserInfoById(int id);

}
