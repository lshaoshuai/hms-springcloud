package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.RedisKeyUtil;
import com.hms.core.support.BaseService;
import com.hms.provider.service.RedisService;
import com.hms.provider.service.UmsUserService;
import com.hms.util.JwtToken;
import com.hms.vo.CodeVo;
import com.hms.vo.UserVo;
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

    @Resource
    RedisService redisService;

    public void send() {
        template.convertAndSend("queue","hello,rabbit~");
    }

    /**
     * 创建Token
     * @param codeVo
     * @return
     */
    @Override
    public String createUserToken(CodeVo codeVo){

        logger.info("获取到用户的手机和验证码为,mobile={} , code={}",codeVo.getPhone_num(),codeVo.getVerifyCode());
        Preconditions.checkNotNull(redisService.getKey(String.valueOf(codeVo.getPhone_num())),"不存在该手机号码");
        Preconditions.checkArgument(redisService.getKey(String.valueOf(codeVo.getPhone_num())).equals(String.valueOf(codeVo.getVerifyCode())),"验证码错误");
        String token = JwtToken.getCodeToken(codeVo);
        redisService.setKey(String.valueOf(codeVo.getPhone_num()),token,10, TimeUnit.DAYS);
        return token;
    }

    /**
     * 创建验证码
     * @param mobile
     * @return
     */
    @Override
    public int createVerifyCode(long mobile){

        //可以简洁的完成参数检验，在进行业务逻辑代码前进行前置判断
        Preconditions.checkArgument(mobile!=11,"手机号码格式错误");
        int num = (int)(Math.random()*9000)+1000;
        redisService.setKey(String.valueOf(mobile),RedisKeyUtil.getVerifyCodeKey(String.valueOf(num)),10, TimeUnit.MINUTES);
        return num;
    }

    @Override
    public boolean queryVerifyCode(long mobile){

        String verifycode = redisService.getKey(String.valueOf(mobile));
        if(null == verifycode){
            return false;
        }else {
            return true;
        }

    }

}
