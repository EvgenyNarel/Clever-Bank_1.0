package org.narel.interceptor;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.narel.exception.TransactionalException;
import org.narel.factory.ObjectFactory;
import org.narel.pool.Pool;

import java.sql.Connection;

@Slf4j
@Aspect
public class TransactionalInterceptor {

    @SneakyThrows
    @Around("execution(* *(..)) && @annotation(org.narel.interceptor.annotations.Transactional)")
    public Object wrapInTransaction(ProceedingJoinPoint joinPoint) {
        Object proceed;
        log.debug("starting the transaction of the '{}#{}{}' method",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());

        Connection connection = null;
        try {
            connection = ObjectFactory.getObject(Pool.class).getConnection();
            connection.setAutoCommit(false);

            proceed = joinPoint.proceed();

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new TransactionalException(e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }

        log.debug("finished the transaction of the '{}#{}' method with output {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                proceed);
        return proceed;
    }
}
