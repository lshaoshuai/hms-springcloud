package com.hms.provider.service.impl;

import com.hms.core.annotation.ServiceLog;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.HotelInfoDao;
import com.hms.provider.model.domain.HotelInfo;
import com.hms.provider.model.vo.HotelInfoVo;
import com.hms.provider.service.HotelQueryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/31 23:03
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class HotelQueryServiceImpl extends BaseService implements HotelQueryService {

    @Autowired
    HotelInfoDao hotelInfoDao;

    @Override
    @ServiceLog(operation = "正在获取宾馆信息....")
    public List<HotelInfoVo> getHotelInfo(int index,int offset){

        logger.info("获取到的索引位置为{},位移数为{}",index,offset);
        List<HotelInfo> hotelInfoList = hotelInfoDao.queryLimitHotelInfo(index,offset);
        List<HotelInfoVo> hotelInfoVos = new ModelMapper().map(hotelInfoList, new TypeToken<List<HotelInfoVo>>() {}.getType());
        return hotelInfoVos;
    }

    @Override
    @ServiceLog(operation = "正在获取宾馆信息....")
    public int getHotelFloorInfo(int hotelid){

        logger.info("获取到的酒店id为 :{}",hotelid);
        int floor = hotelInfoDao.queryHotelFloor(hotelid);
        return floor;
    }
}
