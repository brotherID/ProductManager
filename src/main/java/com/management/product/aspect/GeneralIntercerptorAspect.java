//package com.management.product.aspect;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.stream.IntStream;
//
//@Aspect
//@Slf4j
//@Component
//public class GeneralIntercerptorAspect {
//
//    @Pointcut("execution(* com.management.ovp.api.*.*(..)) || execution(* com.management.ovp.service.*.*(..))")
//    public void loggingPointCut(){
//
//    }
//
//    @Before("loggingPointCut()")
//    public void before(JoinPoint joinPoint){
//        log.info("Before method invoked : {}", joinPoint.getSignature());
//    }
//
//    @After("loggingPointCut()")
//    public void after(JoinPoint joinPoint){
//        log.info("After method invoked : {}",joinPoint.getSignature());
//    }
//
//
//    @Around("loggingPointCut()")
//    public Object logAroundMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
//        Method method = getMethodFromJoinPoint(joinPoint);
//        Object[] args = joinPoint.getArgs();
//
//        // Afficher les noms et valeurs des paramètres
//        StringBuilder params = new StringBuilder();
//        if (method != null) {
//            IntStream.range(0, method.getParameters().length).forEach(i -> {
//                String paramName = method.getParameters()[i].getName();
//                Object paramValue = args[i];
//                params.append(paramName).append("=").append(paramValue).append(", ");
//            });
//        }
//        log.info("Method " + joinPoint.getSignature().getName() + " called with args: {" + params.toString() + "}");
//
//
//        // Exécution de la méthode cible
//        Object result = joinPoint.proceed();
//
//
//        // Log de la valeur de retour
//        log.info("Method " + joinPoint.getSignature().getName() + " returned: " + result);
//
//        return result;
//    }
//
//    // Helper method to get the method from join point
//    private Method getMethodFromJoinPoint(ProceedingJoinPoint joinPoint) {
//        Method method = null;
//        try {
//            method = joinPoint.getTarget().getClass()
//                    .getMethod(joinPoint.getSignature().getName(),
//                            Arrays.stream(joinPoint.getArgs()).map(Object::getClass).toArray(Class[]::new));
//        } catch (NoSuchMethodException e) {
//            log.warn("Could not retrieve method: " + e.getMessage());
//        }
//        return method;
//    }
//}
