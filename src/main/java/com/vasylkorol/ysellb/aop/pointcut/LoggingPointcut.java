package com.vasylkorol.ysellb.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class LoggingPointcut {


    @Pointcut("execution(* com.vasylkorol.ysellb.repository.*.*(..))")
    public void allRepositoriesMethods(){
    }
}
