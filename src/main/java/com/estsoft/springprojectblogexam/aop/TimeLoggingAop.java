package com.estsoft.springprojectblogexam.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLoggingAop {
    //특정 method() 호출했을 때, method의 수행 시간 측정

    //book 패키지 아래에 있는 메소드 호출될 때.
    @Around("execution(* com.estsoft.springprojectblogexam.book..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMs = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finishTimeMs = System.currentTimeMillis();
            long timeMs = finishTimeMs - startTimeMs;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }

}
