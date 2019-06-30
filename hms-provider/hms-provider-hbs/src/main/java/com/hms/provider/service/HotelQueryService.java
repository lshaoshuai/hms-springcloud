package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.dto.SearchHotelDto;
import com.hms.provider.model.vo.HotelInfoVo;

import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/31 23:02
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface HotelQueryService extends IService{

    List<HotelInfoVo> getHotelInfoList(int index, int offset);

    HotelInfoVo getHotelInfo(int hotelid);

    int getHotelFloorInfo(int hotelid);

    List<HotelInfoVo> getHotelByDynamic(SearchHotelDto searchHotelDto);
}
