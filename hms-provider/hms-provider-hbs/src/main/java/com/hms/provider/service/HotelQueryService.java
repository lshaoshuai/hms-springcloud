package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.vo.HotelInfoVo;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/31 23:02
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface HotelQueryService extends IService{

    List<HotelInfoVo> getHotelInfo(int index, int offset);
    int getHotelFloorInfo(int hotelid);
}
