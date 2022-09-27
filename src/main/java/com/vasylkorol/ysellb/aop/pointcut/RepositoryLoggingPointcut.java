package com.vasylkorol.ysellb.aop.pointcut;
import org.aspectj.lang.annotation.Pointcut;

public class RepositoryLoggingPointcut {

    @Pointcut("execution(* com.vasylkorol.ysellb.repository.*.*(..))")
    public void allRepositoriesMethods(){
    }


}
