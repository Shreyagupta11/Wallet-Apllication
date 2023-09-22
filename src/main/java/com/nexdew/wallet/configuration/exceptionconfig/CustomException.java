package com.nexdew.wallet.configuration.exceptionconfig;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public  CustomException(String message) {
        this.message = message;
    }
}
