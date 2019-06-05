package com.hms.provider.service;

import java.util.concurrent.TimeUnit;

/**
 * @author luoshao
 * @date 2019/5/20 10:06
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RedisService {
    /**
     * Gets key.
     *
     * @param key the key
     *
     * @return the key
     */
    Object getKey(String key);

    /**
     *
     * has key
     * @param key
     * @return boolean
     */
    boolean hasKey(String key);
    /**
     * Delete key.
     *
     * @param key the key
     */
    void deleteKey(String key);

    /**
     * Sets key.
     *
     * @param key   the key
     * @param value the value
     */
    void setKey(String key, Object value);

    /**
     * Sets key.
     *
     * @param key     the key
     * @param value   the value
     * @param timeout the timeout 过期时间
     * @param unit    the unit 时间单位
     */
    void setKey(String key, Object value, final long timeout, final TimeUnit unit);
}
