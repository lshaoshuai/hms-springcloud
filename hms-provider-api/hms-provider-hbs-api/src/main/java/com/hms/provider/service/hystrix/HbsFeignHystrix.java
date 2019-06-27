package com.hms.provider.service.hystrix;

import com.hms.provider.service.HbsFeignApi;
import com.hms.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @author luoshao
 * @date 2019/6/22 13:33
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Component
public class HbsFeignHystrix implements HbsFeignApi {

    @Override
    public Wrapper getFloorById(final int id){
        return null;
    }
}
