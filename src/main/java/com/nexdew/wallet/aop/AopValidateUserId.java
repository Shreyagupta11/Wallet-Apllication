package com.nexdew.wallet.aop;

import com.nexdew.wallet.configuration.exceptionconfig.CustomException;
import com.nexdew.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;


@RequiredArgsConstructor
@Aspect
@Component
public class AopValidateUserId {

    @Autowired
    private  UserRepository userRepository;

        @Before("execution(* com.nexdew.wallet.controller.*.*(..))")
    public void validateUsername(JoinPoint joinPoint) throws NoSuchMethodException, CustomException {
            Method method = getMethod(joinPoint);
            Object[] methodArgs = joinPoint.getArgs();

        int usernameIndex = -1;
            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (isUsernameParameter(parameter) && parameter.isAnnotationPresent(PathVariable.class) && methodArgs[i] instanceof Integer) {
                usernameIndex = i;
                break;
            }
        }

            System.out.println("Processing");
        if (usernameIndex != -1) {
            String username = (String) methodArgs[usernameIndex];
            if (userRepository.existsByUsername(username) == Boolean.FALSE) {
                throw new CustomException("The username is not present");
            }
        }
    }

//    private boolean isCustomerIdParameter(Parameter parameter) {
//        return "userId".equals(parameter.getName());
//    }

    private boolean isUsernameParameter(Parameter parameter) {
        return "username".equals(parameter.getName());
    }
    private Method getMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature.getMethod();
    }


}
