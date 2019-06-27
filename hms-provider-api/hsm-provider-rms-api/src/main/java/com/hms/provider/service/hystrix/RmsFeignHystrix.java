package com.hms.provider.service.hystrix;

import com.hms.provider.service.RmsFeignApi;
import com.hms.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @author luoshao
 * @date 2019/6/23 21:34
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Component
public class RmsFeignHystrix implements RmsFeignApi {

    @Override
    public Wrapper getRoomCount(final int hotelid){ return null; }

    @Override
    public Wrapper getRoomTypeInfo(final int id){ return null; }

    @Override
    public Wrapper getLocalRoomInfo(final int id){

        return null;
    }

    @Override
    public Wrapper updateLocalRoomInfo(final int roomid,final int status){
        return null;
    }

}
