package org.narel.interceptor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class ServiceMethodsLogInterceptor {

    @SneakyThrows
    @Around("execution(* org.narel.service..*(..))")
    public Object logAroundAllServiceMethods(ProceedingJoinPoint joinPoint) {
        log.info("starting the call of the '{}#{}{}' method",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());

        Object proceed = joinPoint.proceed();

        log.info("finished the call of the '{}#{}' method with output {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                proceed);

        return proceed;
    }
}