package com.hms.provider.service.impl;

import com.hms.core.support.BaseService;
import com.hms.provider.dao.RoomInfoDao;
import com.hms.provider.domain.RoomInfo;
import com.hms.provider.service.RmsActionService;
import com.hms.provider.vo.RoomVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/4 10:26
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class RmsActionServiceImpl extends BaseService implements RmsActionService {

    @Autowired
    RoomInfoDao roomInfoDao;

    @Override
    public RoomVo[] getRoomsInfo(int hotel_id){

        logger.info("获取到的宾馆ID为{}",hotel_id);
        List<RoomInfo> roomInfos = roomInfoDao.queryRoomInfo(hotel_id);

        RoomVo[] roomVos = new ModelMapper().map(roomInfos, RoomVo[].class);

        return roomVos;
    }
}
