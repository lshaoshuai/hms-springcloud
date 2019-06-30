package com.hms.provider.dao;

import com.hms.provider.model.domain.FavoriteDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

/**
 * @author luoshao
 * @date 2019/6/29 10:33
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface FavoriteDao {

    @Insert("insert into favorite_info (hotel_id,user_id) values(#{hotel_id},#{user_id})")
    int insertFavoriteInfo(FavoriteDo favoriteDo);

    @Update("update favorite_info set favorite = #{is_favorite} where user_id = #{user_id} and hotel_id = #{user_id}")
    int updateFavoriteInfo(FavoriteDo favoriteDo);
}
