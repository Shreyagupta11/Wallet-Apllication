package com.nexdew.wallet.dto.response;

import com.nexdew.wallet.dto.UserDto;
import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ApiResponse {

    private String message;
    private Object data;
    private HttpStatus status;

    public ApiResponse(UserDto userDto, Object data, HttpStatus ok) {
    }
}
