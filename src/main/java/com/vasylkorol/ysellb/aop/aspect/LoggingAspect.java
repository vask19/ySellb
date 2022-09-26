package com.vasylkorol.ysellb.aop.aspect;

import com.vasylkorol.ysellb.aop.pointcut.LoggingPointcut;
import com.vasylkorol.ysellb.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("com.vasylkorol.ysellb.aop.pointcut.LoggingPointcut.allRepositoriesMethods()")
    public void beforeGetProductAdvice(JoinPoint joinPoint){
        MethodSignature methodSignatures = (MethodSignature) joinPoint.getSignature();
        Object[] methodArgs = joinPoint.getArgs();



        log.info("called method {} with args:{}",methodSignatures.getMethod(),methodArgs);

    }
}
