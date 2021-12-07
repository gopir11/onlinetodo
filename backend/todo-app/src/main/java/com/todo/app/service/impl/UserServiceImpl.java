package com.todo.app.service.impl;

import com.todo.app.config.security.JwtTokenUtil;
import com.todo.app.dto.LoginRequest;
import com.todo.app.dto.LoginResponse;
import com.todo.app.dto.UserSignupRequest;
import com.todo.app.exception.AuthorizationException;
import com.todo.app.exception.ValidationException;
import com.todo.app.model.User;
import com.todo.app.repository.UserRepository;
import com.todo.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    /**
     * This api validates the user and add if the user doesn't exists in our system
     *
     * @param userSignupRequest signup request
     * @throws ValidationException If user already present
     */
    @Transactional
    @Override
    public void saveUserDetails(UserSignupRequest userSignupRequest) {
        log.info("User signup request processing started");
        if (userRepository.existsByEmail(userSignupRequest.getEmail())) {
            throw new ValidationException("Email address already in use");
        }
        User user = User.builder()
                .name(userSignupRequest.getName())
                .email(userSignupRequest.getEmail())
                .password(passwordEncoder.encode(userSignupRequest.getPassword()))
                .active(true).build();
        userRepository.save(user);
        log.info("User signup request processing ended");
    }

    /**
     * This api validates the credential of the user and returns JT token
     * @param loginRequest login request
     * @throws AuthorizationException If user credential not valid
     */
    public LoginResponse authenticate(LoginRequest loginRequest) {
        Objects.requireNonNull(loginRequest.getEmail());
        Objects.requireNonNull(loginRequest.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return LoginResponse.builder().token(jwtTokenUtil.generateToken(loginRequest.getEmail())).build();
        } catch (DisabledException e) {
            throw new AuthorizationException("User is disabled", e);
        } catch (BadCredentialsException e) {
            throw new AuthorizationException("Bad credential", e);
        } catch (Exception e) {
            throw new AuthorizationException("User not authorized", e);
        }
    }
}
