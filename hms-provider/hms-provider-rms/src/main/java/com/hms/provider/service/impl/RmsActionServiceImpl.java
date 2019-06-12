package com.hms.provider.service.impl;

import com.hms.core.support.BaseService;
import com.hms.provider.dao.RoomInfoDao;
import com.hms.provider.domain.RoomInfo;
import com.hms.provider.service.RedisService;
import com.hms.provider.service.RmsActionService;
import com.hms.provider.vo.RoomVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.hms.base.constant.GlobalConstant.Room.REDIS_ROOM_STOCK;

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

    @Resource
    RedisService redisService;

    @Override
    public List<RoomVo> getRoomsInfo(int hotel_id){

        logger.info("获取到的宾馆ID为{}",hotel_id);
        List<RoomInfo> roomInfos = roomInfoDao.queryRoomInfo(hotel_id);
        RoomVo[] roomVos = new ModelMapper().map(roomInfos, RoomVo[].class);
        List<RoomVo> roomVoList = new ArrayList<>();
        for(RoomVo roomVo : roomVos) {
            roomVoList.add(roomVo);
        }
        return roomVoList;
    }

    @Override
    public List<RoomVo> getRoomsCountInfo(int hotel_id){

        logger.info("获取到的宾馆ID为{}",hotel_id);
        List<RoomInfo> roomInfos = roomInfoDao.queryRoomInfo(hotel_id);
        RoomVo[] roomVos = new ModelMapper().map(roomInfos, RoomVo[].class);
        List<RoomVo> roomVoList = new ArrayList<>();
        for(RoomVo roomVo : roomVos) {
            roomVo.setRoom_count((int)redisService.getKey(REDIS_ROOM_STOCK + roomVo.getId()));
            roomVoList.add(roomVo);
        }
        return roomVoList;
    }

    @Override
    public int getSingleRoomCountInfo(int room_id){

        logger.info("获取到的宾馆ID为{}",room_id);
        return (int)redisService.getKey(REDIS_ROOM_STOCK + room_id);
    }
}
