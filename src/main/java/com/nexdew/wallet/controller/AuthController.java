package com.nexdew.wallet.controller;

import com.nexdew.wallet.constants.ApiConstant;
import com.nexdew.wallet.dto.UserDto;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.dto.request.AuthRequest;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<ApiResponse> signIn(@RequestBody @Valid AuthRequest authRequest) {
        String token = authService.signIn(authRequest);
        return new ResponseEntity<ApiResponse>(new ApiResponse(token, null, HttpStatus.OK), HttpStatus.OK);
    }

//    @PostMapping(value = "sign-up")
//    public ResponseEntity<ApiResponse> signUp(@RequestBody @Valid User appUser) {
//        String response = authService.signUp(appUser);
//        return new ResponseEntity<>(new ApiResponse(response, null, HttpStatus.OK), HttpStatus.OK);
//    }

    @PostMapping(value = "/sign-up")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') ")
    public ResponseEntity<ApiResponse> signUp(@RequestBody @Valid UserRequest userRequest) {
        UserDto userDto = authService.signUp(userRequest);
        return new ResponseEntity<>(new ApiResponse(ApiConstant.USER_CREATED, userDto, HttpStatus.OK), HttpStatus.OK);
    }

}
