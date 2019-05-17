package com.hms.provider.service.impl;

import com.hms.core.support.BaseService;
import com.hms.dao.RoomDao;
import com.hms.provider.service.RmsOrderService;
import com.hms.vo.RoomVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author luoshao
 * @date 2019/5/16 0:56
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class RmsOrderServiceImpl extends BaseService<RoomVo> implements RmsOrderService {


    @Resource
    private RoomDao roomdao;

    @Override
    public RoomVo getRoomDetail(final String roomNo) {

        logger.info("获取订单明细, orderNo={}", roomNo);
        if(null == roomNo){

        }
        return roomdao.getById(roomNo);
    }

}
