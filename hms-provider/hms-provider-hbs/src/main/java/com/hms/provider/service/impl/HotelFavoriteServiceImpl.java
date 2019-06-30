package com.hms.provider.service.impl;

import com.hms.core.annotation.ServiceLog;
import com.hms.provider.dao.FavoriteDao;
import com.hms.provider.model.domain.FavoriteDo;
import com.hms.provider.service.HotelFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luoshao
 * @date 2019/6/29 10:32
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class HotelFavoriteServiceImpl implements HotelFavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    @Override
    @ServiceLog(operation = "正在获取宾馆信息....")
    public boolean setFavoriteHotel(int hotelid, long userid){

        FavoriteDo favoriteDo = new FavoriteDo();
        favoriteDo.setHotel_id(hotelid);
        favoriteDo.setUser_id(userid);
        favoriteDo.setFavorite(true);
        int line = favoriteDao.insertFavoriteInfo(favoriteDo);
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    @ServiceLog(operation = "正在获取宾馆信息....")
    public boolean cancelFavoriteHotel(int hotelid, long userid){

        FavoriteDo favoriteDo = new FavoriteDo();
        favoriteDo.setFavorite(false);
        favoriteDo.setHotel_id(hotelid);
        favoriteDo.setUser_id(userid);
        int line = favoriteDao.updateFavoriteInfo(favoriteDo);
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

}
