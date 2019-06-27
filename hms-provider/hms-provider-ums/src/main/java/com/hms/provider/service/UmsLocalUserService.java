package com.hms.provider.service;

import com.hms.provider.model.domain.CustomerDo;
import com.hms.provider.model.dto.CustomerDto;
import com.hms.provider.model.dto.LocalUserDto;
import com.hms.provider.model.vo.LocalUserVo;

/**
 * @author luoshao
 * @date 2019/6/24 11:31
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface UmsLocalUserService {

    LocalUserVo checkLocalUserInfo(LocalUserDto localUserDto);

    boolean addCustomer(CustomerDto customerDto);

    CustomerDo queryCustomer(String userid);
}
