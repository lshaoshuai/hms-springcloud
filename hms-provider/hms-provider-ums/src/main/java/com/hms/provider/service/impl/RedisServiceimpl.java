package com.hms.provider.service.impl;

import com.google.common.base.Preconditions;
import com.hms.base.constant.GlobalConstant;
import com.hms.provider.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author luoshao
 * @date 2019/5/20 10:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Slf4j
@Service
public class RedisServiceimpl implements RedisService{

    /**
     *  两者的数据是不共通的；也就是说StringRedisTemplate只能管理StringRedisTemplate里面的数据，
     *  RedisTemplate只能管理RedisTemplate中的数据。
     *  他们两者之间的区别主要在于他们使用的序列化类
     *  redisTemplate.opsForValue();　　//操作字符串
     * redisTemplate.opsForHash();　　 //操作hash
     * redisTemplate.opsForList();　　 //操作list
     * redisTemplate.opsForSet();　　  //操作set
     * redisTemplate.opsForZSet();
     */

    /**
     *
     * @Title: stringRedisTemplate常见用法
        <p>stringRedisTemplate.opsForValue().set("test", "100",60*10,TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间

         stringRedisTemplate.boundValueOps("test").increment(-1);//val做-1操作

        stringRedisTemplate.opsForValue().get("test")//根据key获取缓存中的val

        stringRedisTemplate.boundValueOps("test").increment(1);//val +1

        stringRedisTemplate.getExpire("test")//根据key获取过期时间

        stringRedisTemplate.getExpire("test",TimeUnit.SECONDS)//根据key获取过期时间并换算成指定单位

        stringRedisTemplate.delete("test");//根据key删除缓存

        stringRedisTemplate.hasKey("546545");//检查key是否存在，返回boolean值

        stringRedisTemplate.opsForSet().add("red_123", "1","2","3");//向指定key中存放set集合

        stringRedisTemplate.expire("red_123",1000 , TimeUnit.MILLISECONDS);//设置过期时间

        stringRedisTemplate.opsForSet().isMember("red_123", "1")//根据key查看集合中是否存在指定数据

        stringRedisTemplate.opsForSet().members("red_123");//根据key获取set集合</p>
     */

    @Resource
    private RedisTemplate<String, Object> rt;

    @Override
    public boolean hasKey(String key){

        if (rt.hasKey(key)) {
            log.info("Key is exist");
            return true;
        }
        log.info("Can not find the key");
        return false;
    }

    @Override
    public Object getKey(String key) {
        Object value = null;
        ValueOperations<String, Object> ops = rt.opsForValue();
        if (rt.hasKey(key)) {
            value = ops.get(key);
        }
        log.info("getKey. [OK] key={}, value={}", key, value);
        return value;
    }

    @Override
    public void deleteKey(String key) {
        rt.delete(key);
        log.info("deleteKey. [OK] key={}", key);

    }

    @Override
    public void setKey(String key, Object value) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");

        ValueOperations<String, Object> ops = rt.opsForValue();
        ops.set(key, value);
        rt.expire(key, GlobalConstant.Sys.REDIS_DEFAULT_EXPIRE, TimeUnit.MINUTES);
        log.info("setKey. [OK] key={}, value={}, expire=默认超时时间", key, value);


    }

    @Override
    public void setKey(String key, Object value, long timeout, TimeUnit unit) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(key), "Redis key is not null");
        Preconditions.checkArgument(unit != null, "TimeUnit is not null");
        ValueOperations<String, Object> ops = rt.opsForValue();
        ops.set(key, value);
        rt.expire(key, timeout, unit);
        log.info("setKey. [OK] key={}, value={}, timeout={}, unit={}", key, value, timeout, unit);

    }
}
