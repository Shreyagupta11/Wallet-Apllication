package com.nexdew.wallet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.nexdew.wallet.constants.ApiConstant;
import com.nexdew.wallet.dto.request.UserRequest;
import com.nexdew.wallet.dto.response.ApiResponse;
import com.nexdew.wallet.dto.response.UserResponseDTO;
import com.nexdew.wallet.entity.User;
import com.nexdew.wallet.service.IUserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final ModelMapper modelMapper;


//    @PostMapping("/create")
//    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody UserRequest request){
//        return  ResponseEntity.ok(new ApiResponse(ApiConstant.USER_CREATED, userService.createUser(request), HttpStatus.CREATED));
//    }kjjj

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/user/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') OR hasRole('ROLE_CUSTOMER')")
    public User getUser(@ApiParam("Username") @PathVariable String username){
        User search = this.userService.search(username);
//        UserResponseDTO responseDTO = this.modelMapper.map(search, UserResponseDTO.class);
        return search;
    }


    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') OR hasRole('ROLE_CUSTOMER')")
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(userService.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') OR hasRole('ROLE_CUSTOMER')")
    public UserResponseDTO self(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER') OR hasRole('ROLE_CUSTOMER')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}
