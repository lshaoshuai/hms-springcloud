package com.hms.core.aspect;

import com.hms.core.annotation.ServiceLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author luoshao
 * @date 2019/6/21 10:07
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Aspect
@Component
public class HttpRequestAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestAspect.class);

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.hms.core.annotation.ServiceLog) ")
    public void entryPoint() {
        // 无需内容
    }

    @Before("entryPoint()")
    public void before(JoinPoint joinPoint) {

        log.info("=====================开始执行前置通知==================");
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operation = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operation = method.getAnnotation(ServiceLog.class).operation();// 操作人
                        break;
                    }
                }
            }
            StringBuilder paramsBuf = new StringBuilder();
            for (Object arg : arguments) {
                paramsBuf.append(arg);
                paramsBuf.append("&");
            }

            // *========控制台输出=========*//
            log.info("[X用户]执行了[" + operation + "],类:" + targetName + ",方法名：" + methodName + ",参数:"
                    + paramsBuf.toString());
            log.info("=====================执行前置通知结束==================");
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with exception : " + e.getMessage());
        }

    }

    @After("entryPoint()")
    public void after(JoinPoint joinPoint) {

        log.info("=====================开始执行后置通知==================");
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operation = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operation = method.getAnnotation(ServiceLog.class).operation();// 操作人
                        break;
                    }
                }
            }
            StringBuilder paramsBuf = new StringBuilder();
            for (Object arg : arguments) {
                paramsBuf.append(arg);
                paramsBuf.append("&");
            }

            // *========控制台输出=========*//
            log.info("[X用户]执行了[" + operation + "],类:" + targetName + ",方法名：" + methodName + ",参数:"
                    + paramsBuf.toString());
            log.info("=====================执行后置通知结束==================");
        } catch (Throwable e) {
            log.info("around " + joinPoint + " with exception : " + e.getMessage());
        }

    }
    /**
     * 环绕通知处理处理
     *
     * @param
     * @throws Throwable
     */
    @Around("entryPoint()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 先执行业务,注意:业务这样写业务发生异常不会拦截日志。
        Object result = point.proceed();
        try {
            handleAround(point);// 处理日志
        } catch (Exception e) {
            log.error("日志记录异常", e);
        }
        return result;
    }

    /**
     * around日志记录
     *
     * @param point
     * @throws SecurityException
     * @throws NoSuchMethodException
     */
    public void handleAround(ProceedingJoinPoint point) throws Exception {
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        // 方法名称
        String methodName = currentMethod.getName();
        // 获取注解对象
        ServiceLog aLog = currentMethod.getAnnotation(ServiceLog.class);
        // 类名
        String className = point.getTarget().getClass().getName();
        // 方法的参数
        Object[] params = point.getArgs();

        StringBuilder paramsBuf = new StringBuilder();
        for (Object arg : params) {
            paramsBuf.append(arg);
            paramsBuf.append("&");
        }
        // 处理log。。。。
        log.info("[X用户]执行了[" + aLog.operation() + "],类:" + className + ",方法名：" + methodName + ",参数:"
                + paramsBuf.toString());

    }

    @AfterThrowing(pointcut = "entryPoint()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // 通过request获取登陆用户信息
        // HttpServletRequest request = ((ServletRequestAttributes)
        // RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class<?> targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operation = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operation = method.getAnnotation(ServiceLog.class).operation();
                        break;
                    }
                }
            }

            StringBuilder paramsBuf = new StringBuilder();
            for (Object arg : arguments) {
                paramsBuf.append(arg);
                paramsBuf.append("&");
            }

            log.info("异常方法:" + className + "." + methodName + "();参数:" + paramsBuf.toString() + ",处理了:" + operation);
            log.info("异常信息:" + e.getMessage());
        } catch (Exception ex) {
            log.error("异常信息:{}", ex.getMessage());
        }
    }
}
