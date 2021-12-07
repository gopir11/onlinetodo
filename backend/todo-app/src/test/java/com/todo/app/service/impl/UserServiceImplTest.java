package com.todo.app.service.impl;

import com.todo.app.config.security.JwtTokenUtil;
import com.todo.app.dto.LoginRequest;
import com.todo.app.dto.UserSignupRequest;
import com.todo.app.exception.AuthorizationException;
import com.todo.app.exception.ValidationException;
import com.todo.app.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Test(expected = ValidationException.class)
    public void testSaveUserDetailsUserAlreadyExists() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        userService.saveUserDetails(getSignupRequest());
    }

    @Test
    public void testSaveUserDetails() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("Test");
        userService.saveUserDetails(getSignupRequest());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testAuthenticateSuccess() {
        when(jwtTokenUtil.generateToken(anyString())).thenReturn("Test");
        userService.authenticate(getLoginRequest());
        verify(jwtTokenUtil, times(1)).generateToken(anyString());
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test(expected = AuthorizationException.class)
    public void testAuthenticateDisabledException() {
        when(authenticationManager.authenticate(any())).thenThrow(new DisabledException("Test"));
        userService.authenticate(getLoginRequest());
    }

    @Test(expected = AuthorizationException.class)
    public void testAuthenticateBadCredentialsException() {
        when(authenticationManager.authenticate(any())).thenThrow(new BadCredentialsException("Test"));
        userService.authenticate(getLoginRequest());
    }

    @Test(expected = AuthorizationException.class)
    public void testAuthenticateException() {
        when(authenticationManager.authenticate(any())).thenThrow(new NullPointerException("Test"));
        userService.authenticate(getLoginRequest());
    }

    private UserSignupRequest getSignupRequest() {
        return UserSignupRequest.builder().email("test@test.com").name("name").password("Password").build();
    }

    private LoginRequest getLoginRequest() {
        return LoginRequest.builder().email("test@test.com").password("Password").build();
    }
}
