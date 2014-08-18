package com.epam.lab.news.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ParserExceptionInterceptor {

    @AfterThrowing(pointcut = "execution(* com.epam.lab.news.service.CommentParser.*(..))",
            throwing = "exception")
    public void handleParserException(Throwable exception){

    }

}
