package com.calibre.forex.fetching.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Pointcut("execution(* com.calibre.forex.fetching.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object handlerException(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable ex) {
            logger.error("execute fetching occur exception.", ex);
        }
        return null;
    }
}
