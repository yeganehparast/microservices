package com.microservice.assignment.starbux.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(com.microservice.assignment.starbux.aspect.LogMehtod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(String.format("method: %s has been started.", joinPoint.getSignature().toShortString()));
        try {
            Object proceed = joinPoint.proceed();
            log.info(String.format("method: %s has been completed.", joinPoint.getSignature().toShortString()));
            return proceed;
        } catch (Exception e) {
            log.error(String.format("method: %s has been terminated.", joinPoint.getSignature().toShortString()));
            throw e;
        }
    }
}
