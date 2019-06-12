package com.hms.provider;

import com.hms.provider.service.RedisService;
import com.hms.provider.service.RmsActionService;
import com.hms.provider.vo.RoomVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static com.hms.base.constant.GlobalConstant.Room.REDIS_ROOM_STOCK;

/**
 * @author luoshao
 * @date 2019/6/4 10:21
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Component
public class RoomServiceInitialize implements InitializingBean {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    RmsActionService rmsActionService;

    @Resource
    RedisService redisService;

    @Override
    public void afterPropertiesSet() throws Exception {

        logger.info("InitSequenceBean: afterPropertiesSet");
        List<RoomVo> roomVos = rmsActionService.getRoomsInfo(1);
        if(roomVos == null){
            return;
        }
        for(RoomVo roomVo : roomVos){
            redisService.deleteKey(REDIS_ROOM_STOCK + roomVo.getId());
            redisService.setKey(REDIS_ROOM_STOCK + roomVo.getId(),roomVo.getRoom_count());
        }
        logger.info("Initialize Finish");
    }
}
