package com.hms.core.support;

import com.hms.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class Base service.
 *
 * @param <T> the type parameter
 *
 * @author paascloud.net@gmail.com
 */
public abstract class BaseService<T> implements IService<T> {

    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected long generateId() {
        return IdWorker.getInstance(0,1).nextId();
    }

}
