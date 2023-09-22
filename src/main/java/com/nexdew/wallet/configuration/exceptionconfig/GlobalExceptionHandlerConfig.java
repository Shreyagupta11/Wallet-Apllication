package com.nexdew.wallet.configuration.exceptionconfig;

import com.nexdew.wallet.dto.response.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerConfig {

    // Global exception handler wouldn't handle Filter exceptions. For more reference read this article
    // https://iamvickyav.medium.com/remember-springboot-global-exceptionhandler-wont-work-for-filters-5fabb4a2662a
    // https://www.baeldung.com/spring-security-exceptionhandler


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleInvalidArgument(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(new ApiResponse("Modal validation fails.", errorMap, HttpStatus.NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), null, e.getHttpStatus()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthenticationException.class, AccessDeniedException.class})
    public ResponseEntity<ApiResponse> handleAccessDeniedException(Exception e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse> handleDataIntegrity(DataIntegrityViolationException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), null, HttpStatus.OK), HttpStatus.OK);
    }

    @ExceptionHandler(RequestRejectedException.class)
    public ResponseEntity<ApiResponse> handleRequestRejectedException(RequestRejectedException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), null, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

}
