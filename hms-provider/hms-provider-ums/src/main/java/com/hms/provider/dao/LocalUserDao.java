package com.hms.provider.dao;

import com.hms.provider.model.domain.LocalUserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoshao
 * @date 2019/6/24 12:17
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface LocalUserDao {

    @Select("select * from local_user_info where username = #{username}")
    LocalUserDO getByUsername(@Param("username")String username);

    @Insert("insert into local_user_info () values ()")
    boolean insertLocalUserInfo(LocalUserDO localUserDO);

}
