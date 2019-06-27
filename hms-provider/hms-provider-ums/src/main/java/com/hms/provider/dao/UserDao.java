package com.hms.provider.dao;

import com.hms.provider.model.vo.UserVo;
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


}
