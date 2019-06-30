package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.base.dto.LocalUserTokenDto;
import com.hms.provider.dao.CustomDao;
import com.hms.provider.dao.LocalUserDao;
import com.hms.provider.model.domain.CustomerDo;
import com.hms.provider.model.domain.LocalUserDO;
import com.hms.provider.model.dto.CustomerDto;
import com.hms.provider.model.dto.LocalUserDto;
import com.hms.provider.model.vo.LocalUserVo;
import com.hms.provider.service.RedisService;
import com.hms.provider.service.UmsLocalUserService;
import com.hms.provider.util.JwtToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.hms.base.constant.GlobalConstant.User.TOKEN_CODE;

/**
 * @author luoshao
 * @date 2019/6/24 11:32
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class UmsLocalUserServiceImpl implements UmsLocalUserService {


    @Autowired
    CustomDao customerDao;

    @Autowired
    LocalUserDao localUserDao;

    @Resource
    RedisService redisService;

    @Override
    @Transactional
    public LocalUserVo checkLocalUserInfo(LocalUserDto localUserDto){

        LocalUserDO localUserDO = localUserDao.getByUsername(localUserDto.getUsername());
        Preconditions.checkArgument(localUserDO.getUsername().equals(localUserDto.getUsername()),"不存在改用户名");
        Preconditions.checkArgument(localUserDO.getPassword().equals(localUserDto.getPassword()),"密码错误");
        LocalUserTokenDto localUserTokenDto = new LocalUserTokenDto();
        localUserTokenDto.setUsername(localUserDO.getUsername());
        localUserTokenDto.setRole(localUserDO.getRole());
        localUserTokenDto.setUsertoken(JwtToken.getUserToken(localUserDO.getUsername(),localUserDO.getPassword()));
        localUserTokenDto.setHotelid(localUserDto.getHotelid());
        LocalUserVo localUserVo  = new ModelMapper().map(localUserTokenDto, LocalUserVo.class);
        redisService.setKey(TOKEN_CODE + localUserTokenDto ,localUserTokenDto.getUsertoken(),10, TimeUnit.HOURS);
        redisService.setKey(localUserTokenDto.getUsertoken(), localUserTokenDto,10, TimeUnit.HOURS);
        return localUserVo;
    }

    public boolean addCustomer(CustomerDto customerDto){
        CustomerDo customerDo = new CustomerDo();
        customerDo.setPhone(customerDto.getPhone());
        customerDo.setUsername(customerDto.getUsername());
        customerDo.setUser_id(customerDto.getUserId());
        int line = customerDao.insertCustomerInfo(customerDo);
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

    public CustomerDo queryCustomer(String userid){

        CustomerDo customerDo = customerDao.selectCustomInfo(userid);
        Preconditions.checkNotNull(customerDo,"不存在该用户");
        return customerDo;
    }

}