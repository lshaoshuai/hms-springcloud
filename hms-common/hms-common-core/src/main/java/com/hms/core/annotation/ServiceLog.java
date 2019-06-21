package com.hms.core.annotation;

import com.hms.core.enums.LogType;

import java.lang.annotation.*;

/**
 * @author luoshao
 * @date 2019/6/21 10:17
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {

    public String operation();

    public LogType level() default LogType.INFO;
}
