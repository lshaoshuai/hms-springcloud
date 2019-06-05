package com.hms.core.annotation;

/**
 * @author luoshao
 * @date 2019/5/31 16:21
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

import com.hms.core.validator.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/***
 * //@Retention ：用来说明该注解类的生命周期。它有以下三个参数：
 * RetentionPolicy.SOURCE : 注解只保留在源文件中
 * RetentionPolicy.CLASS : 注解保留在class文件中，在加载到JVM虚拟机时丢弃
 * RetentionPolicy.RUNTIME : 注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解。
 *
 * //@Target : 用来说明该注解可以被声明在那些元素之前。
 * ElementType.TYPE：说明该注解只能被声明在一个类前。
 * ElementType.FIELD：说明该注解只能被声明在一个类的字段前。
 * ElementType.METHOD：说明该注解只能被声明在一个类的方法前。
 * ElementType.PARAMETER：说明该注解只能被声明在一个方法参数前。
 * ElementType.CONSTRUCTOR：说明该注解只能声明在一个类的构造方法前。
 * ElementType.LOCAL_VARIABLE：说明该注解只能声明在一个局部变量前。
 * ElementType.ANNOTATION_TYPE：说明该注解只能声明在一个注解类型前。
 * ElementType.PACKAGE：说明该注解只能声明在一个包名前。
 *
 * //@Constraint来限定自定义注解的方法
 */
@Constraint(validatedBy = IsMobileValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsMobile {

    boolean required() default true;

    String message() default "手机号格式错误";

    Class<?>[] groups() default {}; //分组校验

    Class<? extends Payload>[] payload() default {};
}
