package com.hms.provider.web.rpc;

import com.hms.annotation.NoNeedAccessAuthentication;
import com.hms.core.support.BaseController;
import com.hms.provider.dao.LocalRoomDao;
import com.hms.provider.dao.RoomInfoDao;
import com.hms.provider.model.domain.LocalRoomDo;
import com.hms.provider.model.domain.RoomInfo;
import com.hms.provider.service.RmsFeignApi;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoshao
 * @date 2019/6/24 16:14
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RefreshScope
@RestController
@Api(value = "API - RmsFeignClient", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RmsFeignClient extends BaseController implements RmsFeignApi {

    @Autowired
    LocalRoomDao localRoomDao;

    @Autowired
    RoomInfoDao roomInfoDao;

    @Override
    @ApiOperation(httpMethod = "GET", value = "统计客房总数")
    @NoNeedAccessAuthentication
    public Wrapper getRoomCount(@RequestParam("hotelid") int hotelid){
        int count = localRoomDao.countRoomTotal(hotelid);
        return WrapMapper.ok(count);
    }

    @Override
    @ApiOperation(httpMethod = "GET", value = "统计客房总数")
    @NoNeedAccessAuthentication
    public Wrapper getEmptyRoomCount(@RequestParam("hotelid") int hotelid,@RequestParam("status") int status,@RequestParam("type") String type,@RequestParam("value") String value){
        int count = localRoomDao.countDynamicRoomTotal(hotelid,type,value,status);
        return WrapMapper.ok(count);
    }


    @Override
    @ApiOperation(httpMethod = "GET", value = "获取客房类型信息")
    public Wrapper getRoomTypeInfo(@RequestParam("id") int id){
        RoomInfo roomInfo = roomInfoDao.queryRoomInfoById(id);
        return WrapMapper.ok(roomInfo);
    }

    @Override
    @ApiOperation(httpMethod = "POST", value = "更新客服状态")
    public Wrapper updateLocalRoomInfo(@RequestParam("roomId") int roomId, @RequestParam("checkInStatus") int checkInStatus){

        int line = localRoomDao.updateLocalRoomInfoStatusById(checkInStatus,roomId);
        if(line >= 0){
            return WrapMapper.ok(true);
        }else {
            return WrapMapper.ok(false);
        }

    }

    @Override
    @ApiOperation(httpMethod = "GET", value = "获取本地客房信息")
    public Wrapper getLocalRoomInfo(@RequestParam("id") int id){

        LocalRoomDo localRoomDo = localRoomDao.queryLocalRoomInfoById(id);
        return WrapMapper.ok(localRoomDo);
    }

    @Override
    @ApiOperation(httpMethod = "GET", value = "统计客房总数")
    @NoNeedAccessAuthentication
    public Wrapper getRoomPrice(@RequestParam("roomid") int roomid){
        int count = localRoomDao.queryRoomPrice(roomid);
        return WrapMapper.ok(count);
    }

    @Override
    @ApiOperation(httpMethod = "GET", value = "随机获取客房")
    @NoNeedAccessAuthentication
    public Wrapper getRandomRoom(@RequestParam("roomname") String roomname){

        logger.info("随机获取到的客房类型为：{}",roomname);
        int room_id = localRoomDao.queryRandomRoomByType(roomname);
        logger.info("随机获取到的客房号为：{}",room_id);
        return WrapMapper.ok(room_id);
    }

}
