package com.kiko.rent.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;


@Aspect
@Component
public class ServiceLoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void callAtMyServicePublic() { }

    @Before("callAtMyServicePublic()")
    public void beforeCallAtMethod(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        logger.info(" ###### beforeService " + jp.toString() + ", args=[" + args + "]");
    }

    @After("callAtMyServicePublic()")
    public void afterCallAt(JoinPoint jp) {
        logger.info(" ###### afterService " + jp.toString());
    }

    @AfterReturning(pointcut = "within(@org.springframework.stereotype.Service *)", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info(" ###### Returning for Service : {} ; Method : {} ", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        if (result != null) {
            logger.info(" ###### with value : {}", result.toString());
        } else{
            logger.info(" ###### with null as return value.");
        }
    }
}