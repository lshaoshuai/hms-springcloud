package com.hms.provider.service.hystrix;

import com.hms.provider.service.OmsFeignApi;
import com.hms.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @author luoshao
 * @date 2019/6/25 22:12
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Component
public class OmsFeignHystrix implements OmsFeignApi {

    @Override
    public Wrapper getOrderCount() {
        return null;
    }
}
