package com.todo.app.controller;

import com.todo.app.common.response.Response;
import com.todo.app.dto.LoginRequest;
import com.todo.app.dto.LoginResponse;
import com.todo.app.dto.UserSignupRequest;
import com.todo.app.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.todo.app.common.util.ResponseUtil.createdResponse;
import static com.todo.app.common.util.ResponseUtil.okResponse;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/signup")
    @ApiOperation(value = "New user signup", response = HttpStatus.class)
    public ResponseEntity<Response<HttpStatus>> signup(@ApiParam(value = "Signup request") @Validated @RequestBody UserSignupRequest userSignupRequest) {
        userService.saveUserDetails(userSignupRequest);
        return createdResponse(HttpStatus.CREATED);
    }

    @PostMapping(value = "/users/login")
    @ApiOperation(value = "User authentication", response = HttpStatus.class)
    public ResponseEntity<Response<LoginResponse>> login(@ApiParam(value = "Login request") @Validated @RequestBody LoginRequest loginRequest) {
        return okResponse(userService.authenticate(loginRequest));
    }

}
