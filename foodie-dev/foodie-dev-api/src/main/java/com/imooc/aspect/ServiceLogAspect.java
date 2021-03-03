package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    /**
     * AOP 通知：
     * 1. 前置通知： 在方法调用之前执行
     * 2. 后置通知： 在方法正常调用之后执行
     * 3. 环绕通知： 在方法调用之前和之后， 都可以执行的通知
     * 4. 异常通知： 如果在方法调用过程中发生异常， 则通知
     * 5. 最终通知： 在方法调用之后执行
     * */

    /**
     * AOP通知：
     * 1. 前置通知：在方法调用之前执行
     * 2. 后置通知：在方法正常调用之后执行
     * 3. 环绕通知：在方法调用之前和之后，都分别可以执行的通知
     * 4. 异常通知：如果在方法调用过程中发生异常，则通知
     * 5. 最终通知：在方法调用之后执行
     */

    /**
     * 切面表达式：
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    public static final Logger log = LoggerFactory.getLogger(ServiceLogAspect.class);

    //@Around("execution(* com.imooc.service.impl..*.*(..))")
    @Around("execution(* com.imooc.service.impl..*.*(..))")
    // api service impl 下 所有的子包和子包的方法
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // previous wrong, since did not take the parameter
//        log.info("====== start ========",
//                joinPoint.getTarget().getClass(),
//                joinPoint.getSignature().getName());
        log.info("====== start {}.{} ========",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());

        long begin = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        long takeTime = end - begin;

        if (takeTime > 3000) {
            log.error("====end, takes: {} ms====", takeTime);
        } else if (takeTime > 2000) {
            log.warn("====end, takes: {} ms====", takeTime);
        } //else if (takeTime > 1000) {
            else {
            log.info("====end, takes: {} ms====", takeTime);
        }
        return result;
    }
}
