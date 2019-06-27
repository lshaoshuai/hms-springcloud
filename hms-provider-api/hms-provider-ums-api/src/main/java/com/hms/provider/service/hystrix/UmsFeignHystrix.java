package com.hms.provider.service.hystrix;

import com.hms.provider.model.dto.CustomerDto;
import com.hms.provider.service.UmsFeignApi;
import com.hms.wrapper.Wrapper;
import org.springframework.stereotype.Component;

/**
 * @author luoshao
 * @date 2019/6/20 20:17
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Component
public class UmsFeignHystrix implements UmsFeignApi {

    @Override
    public Wrapper addCustomerInfo(final CustomerDto customerDto){
        return null;
    }

    @Override
    public Wrapper queryCustomerInfo(final String userid){
        return null;
    }
}
