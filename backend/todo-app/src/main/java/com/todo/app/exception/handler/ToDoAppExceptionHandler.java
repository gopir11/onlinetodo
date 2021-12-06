package com.todo.app.exception.handler;

import com.todo.app.common.response.Error;
import com.todo.app.common.response.ErrorResponse;
import com.todo.app.common.response.MetaData;
import com.todo.app.exception.AuthorizationException;
import com.todo.app.exception.NoDataFoundException;
import com.todo.app.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ToDoAppExceptionHandler {

    @ExceptionHandler({
            AuthorizationException.class,
            LockedException.class,
            DisabledException.class,
            AccountExpiredException.class,
            BadCredentialsException.class,
            CredentialsExpiredException.class,
            UsernameNotFoundException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse handleAuthorizationException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return getErrorResponse(ex, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            NoDataFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNotFoundException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return getErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            ValidationException.class,
            HttpRequestMethodNotSupportedException.class,
            ConstraintViolationException.class,
            IllegalAccessException.class,
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotValidException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return getErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleError(Exception ex) {
        log.error(ex.getMessage(), ex);
        return getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private MetaData getMetaData(HttpStatus httpStatus) {
        return MetaData.builder().success(false).description(httpStatus.getReasonPhrase()).build();
    }

    private Error getError(HttpStatus httpStatus, Exception ex) {
        return Error.builder().code(String.valueOf(httpStatus.value())).description(ex.getMessage()).build();
    }

    private ErrorResponse getErrorResponse(Exception ex, HttpStatus httpStatus) {
        return new ErrorResponse(getMetaData(httpStatus), getError(httpStatus, ex));
    }

}
