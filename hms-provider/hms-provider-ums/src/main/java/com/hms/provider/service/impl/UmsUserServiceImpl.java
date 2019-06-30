package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.RedisKeyUtil;
import com.hms.base.dto.UserTokenDto;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.UserDao;
import com.hms.provider.dao.UserTokenDao;
import com.hms.provider.model.domain.UserDo;
import com.hms.provider.model.dto.CodeDto;
import com.hms.provider.model.vo.UserTokenVo;
import com.hms.provider.model.vo.UserVo;
import com.hms.provider.service.RedisService;
import com.hms.provider.service.UmsUserService;
import com.hms.provider.util.JwtToken;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.hms.base.constant.GlobalConstant.User.TOKEN_CODE;
import static com.hms.base.constant.GlobalConstant.User.VERIFY_CODE;
import static com.hms.base.constant.MqConstant.CODE_EXCHANGE;
import static com.hms.base.constant.MqConstant.CODE_ROUTING_KEY;

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

    @Autowired
    UserDao userDao;

    @Resource
    RedisService redisService;


    /**
     * 创建Token
     * @param codedto
     * @return
     */
    @Override
    public UserTokenVo createUserToken(CodeDto codedto){

        logger.info("获取到用户的手机和验证码为,mobile={} , code={}",codedto.getPhone_num(),codedto.getVerifyCode());
        UserDo user = userDao.selectUserInfoByMobile(codedto.getPhone_num());
        //如果数据库中不存在该用户的数据,则创建用户
        String token = "";
        if(user == null){

            user = new UserDo();
            user.setId(generateId());
            user.setPhone_num(codedto.getPhone_num());
            user.setUser_name(UUID.randomUUID().toString().replaceAll("-", ""));
            user.setEmail("1939125539@qq.com");
            user.setUser_id("400230103123123");
            logger.info("创建了用户： {}",user);
            userDao.insertOrUpdateUserinfo(user);
            token = JwtToken.getCodeToken(codedto);
            UserTokenDto userTokenDto = new ModelMapper().map(user, UserTokenDto.class);
            redisService.setKey(TOKEN_CODE + codedto.getPhone_num(),token,10, TimeUnit.HOURS);
            redisService.setKey(token, userTokenDto,10, TimeUnit.HOURS);


        }else{
//            Preconditions.checkNotNull(redisService.getKey(VERIFY_CODE + codedto.getPhone_num()),"该手机号码登陆过期");
            Preconditions.checkArgument(redisService.getKey(VERIFY_CODE + codedto.getPhone_num()).equals(String.valueOf(codedto.getVerifyCode())),"验证码错误");
            token = JwtToken.getCodeToken(codedto);
            UserTokenDto userTokenDto = new ModelMapper().map(user, UserTokenDto.class);
            redisService.setKey(TOKEN_CODE + codedto.getPhone_num(),token,10, TimeUnit.HOURS);
            redisService.setKey(token, userTokenDto,10, TimeUnit.HOURS);
        }
        UserTokenVo userTokenVo = new ModelMapper().map(user , UserTokenVo.class);
        userTokenVo.setAccesstoken(token);
        return userTokenVo;
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
        redisService.setKey(VERIFY_CODE + mobile,RedisKeyUtil.getVerifyCodeKey(String.valueOf(num)),10, TimeUnit.MINUTES);
        template.convertAndSend(CODE_EXCHANGE, CODE_ROUTING_KEY, String.valueOf(num)); //
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
