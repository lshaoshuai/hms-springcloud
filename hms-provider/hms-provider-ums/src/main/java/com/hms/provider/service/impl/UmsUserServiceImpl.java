package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.RadomUtil;
import com.hms.RedisKeyUtil;
import com.hms.base.dto.UserTokenDto;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.UserTokenDao;
import com.hms.provider.dto.CodeDto;
import com.hms.provider.dto.UmsTokenDto;
import com.hms.provider.service.RedisService;
import com.hms.provider.service.UmsUserService;
import com.hms.provider.util.JwtToken;
import com.hms.provider.vo.UserVo;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author luoshao
 * @date 2019/5/18 20:01
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class UmsUserServiceImpl extends BaseService<UserVo> implements UmsUserService{

    @Autowired
    private AmqpTemplate template;

    @Autowired
    UserTokenDao userTokenDao;

    @Resource
    RedisService redisService;

    public void send() {
        template.convertAndSend("queue","hello,rabbit~");
    }

    /**
     * 创建Token
     * @param codedto
     * @return
     */
    @Override
    public String createUserToken(CodeDto codedto){

        logger.info("获取到用户的手机和验证码为,mobile={} , code={}",codedto.getPhone_num(),codedto.getVerifyCode());
        Preconditions.checkNotNull(redisService.getKey(codedto.getPhone_num()),"不存在该手机号码");
        Preconditions.checkArgument(redisService.getKey(codedto.getPhone_num()).equals(String.valueOf(codedto.getVerifyCode())),"验证码错误");
        String token = JwtToken.getCodeToken(codedto);


        UmsTokenDto userTokenDto= new UmsTokenDto();
        userTokenDto.setId(Integer.parseInt(codedto.getPhone_num()));
        userTokenDto.setPhone_num(codedto.getPhone_num());
        userTokenDto.setUser_name(RadomUtil.createRadomUserName());
        userTokenDto.setUser_token(token);
        userTokenDao.insertUserinfo(userTokenDto);
        //ModelMapper负责将DO和DTO来回转换
        UserTokenDto userdto = new ModelMapper().map(userTokenDto, UserTokenDto.class);
        redisService.setKey(codedto.getPhone_num(),token,10, TimeUnit.DAYS);
        redisService.setKey(token, userdto,10, TimeUnit.HOURS);
        logger.info("userdto{}",userdto);
        return token;
    }



    /**
     * 创建验证码
     * @param mobile
     * @return
     */
    @Override
    public int createVerifyCode(String mobile){

        //可以简洁的完成参数检验，在进行业务逻辑代码前进行前置判断
//        Preconditions.checkArgument(mobile.length()>= 11 ,"手机号码格式错误");

        int num = (int)(Math.random()*9000)+1000;
        redisService.setKey(mobile,RedisKeyUtil.getVerifyCodeKey(String.valueOf(num)),10, TimeUnit.MINUTES);
        return num;
    }

    @Override
    public boolean queryVerifyCode(long mobile){

        String verifycode = (String)redisService.getKey(String.valueOf(mobile));
        if(null == verifycode){
            return false;
        }else {
            return true;
        }

    }

}
