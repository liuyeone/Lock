package com.example.lock.aop;

import com.example.lock.annotation.RetryOnOptimisticLockingFailure;
import com.example.lock.exception.ObjectOptimisticLockingFailureException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Aspect
@Component
public class RetryOnOptimisticLockingAspect {

    // 最多重试的次数
    public static final int maxRetries = 5;

    @Around("@annotation(retryOnOptimisticLockingFailure)")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp, RetryOnOptimisticLockingFailure retryOnOptimisticLockingFailure) throws Throwable {
        int numAttempts = 0;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (Exception e) {
                if (e instanceof ObjectOptimisticLockingFailureException) {
                    log.info("更新数据---乐观锁重试中---");
                    if (numAttempts > maxRetries) {
                        log.info("抛出异常");
                        throw e;
                    }
                }
            }
        } while (numAttempts < this.maxRetries);
        return null;
    }
}
