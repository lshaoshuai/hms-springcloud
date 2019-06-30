package com.hms.provider.service;

import com.hms.core.support.IService;

/**
 * @author luoshao
 * @date 2019/6/29 10:30
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface HotelFavoriteService extends IService {

    boolean setFavoriteHotel(int hotelid,long userid);

    boolean cancelFavoriteHotel(int hotelid, long userid);
}
