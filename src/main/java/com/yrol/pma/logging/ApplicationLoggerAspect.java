package com.yrol.pma.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP programming: https://docs.spring.io/spring-framework/docs/2.5.5/reference/aop.html
 * The Logger class for the app using Aspect-Oriented Programming (AOP) Pointcut
 * @Aspect - used to mark the code that run throughout the application
 * @Pointcut - will define where the logging should run within the code
 * @Component - need to have this annotation as ApplicationLoggerAspect class can be scanned and picked up as a component
 * JoinPoint - point during the execution time which will give access to the parts of the code. Allows to access methods, variables arguments & etc.
 * */
@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Adding multiple locations to Pointcut which the logging should trigger. In this case when hitting controllers and data objects using package name.
     * Likewise, we can target specific methods: ex- "com.yrol.pma.controllers.HomeController.displayHome"
     * */
    @Pointcut("within(com.yrol.pma.controllers..*)"
            + "|| within(com.yrol.pma.dao..*)")
    public void definePackagePointcuts(){
        //empty method just to name the location specified in the pointcut
    }

    /**
     * METHOD 1: Running @After or @Before separately.
     * Run after a certain method or package is called. Likewise, @Before, @Around & etc can also be done.
     * Logging controller name, method name and it's arguments (ex: addAttribute values in displayHome method in HomeController).
     * */
//    @After("definePackagePointcuts()")
//    public void logAfter(JoinPoint jp) {
//        log.debug("\n \n \n");
//        log.debug("******** Before Method Execution ********* \n {} --> {} with argument[s] = {}",
//                jp.getSignature().getDeclaringTypeName(),
//                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//        log.debug("______________________________________________ \n \n \n");
//    }

    /**
     * METHOD 2: Combining @After and @Before using ProceedingJoinPoint
     * Logging controller name, method name and it's arguments (ex: addAttribute values in displayHome method in HomeController) - on both Before and after
     *
     * @return
     */
    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) throws Throwable {

        //Before
        log.debug("\n \n \n");
        log.debug("******** Before Method Execution ********* \n {} --> {} with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
        log.debug("______________________________________________ \n \n \n");

        Object jpo = null;

        try {
            jpo = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //After
        log.debug("\n \n \n");
        log.debug("******** After Method Execution ********* \n {} --> {} with argument[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
        log.debug("______________________________________________ \n \n \n");

        return jpo;
    }
}
