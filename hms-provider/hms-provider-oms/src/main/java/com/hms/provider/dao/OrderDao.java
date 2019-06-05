package com.hms.provider.dao;

import com.hms.provider.dto.OrderDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author luoshao
 * @date 2019/5/16 10:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Mapper
public interface OrderDao {

    @Insert("")
    OrderDto insertOrderInfo(int room_id);


}
