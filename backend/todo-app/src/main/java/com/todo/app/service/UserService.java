package com.todo.app.service;

import com.todo.app.dto.LoginRequest;
import com.todo.app.dto.LoginResponse;
import com.todo.app.dto.UserSignupRequest;
import com.todo.app.exception.AuthorizationException;
import com.todo.app.exception.ValidationException;

/**
 * Interface that defines the user operations contract
 */
public interface UserService {
    /**
     * This api validates the user and add if the user doesn't exists in our system
     * @param userSignupRequest signup request
     * @throws ValidationException If user already present
     */
    void saveUserDetails(UserSignupRequest userSignupRequest);

    /**
     * This api validates the credential of the user and returns JT token
     * @param loginRequest login request
     * @throws AuthorizationException If user credential not valid
     */
    LoginResponse authenticate(LoginRequest loginRequest);
}
