package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.vo.RoomVo;

/**
 * @author luoshao
 * @date 2019/5/16 0:55
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RmsOrderService  extends IService<RoomVo> {

    /**
     * 查询订单详情.
     *
     * @param orderNo the order no
     *
     * @return the order detail
     */
    RoomVo getRoomDetail(String orderNo);

}