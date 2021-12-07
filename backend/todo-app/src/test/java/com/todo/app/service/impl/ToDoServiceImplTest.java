package com.todo.app.service.impl;

import com.todo.app.config.security.JwtTokenUtil;
import com.todo.app.config.security.UserPrincipal;
import com.todo.app.dto.*;
import com.todo.app.exception.AuthorizationException;
import com.todo.app.exception.NoDataFoundException;
import com.todo.app.exception.ValidationException;
import com.todo.app.model.Todo;
import com.todo.app.model.User;
import com.todo.app.repository.TodoRepository;
import com.todo.app.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceImplTest {
    @InjectMocks
    private ToDoServiceImpl toDoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private Authentication auth;

    @Before
    public void initSecurityContext() {
        when(auth.getPrincipal()).thenReturn(UserPrincipal.create(User.builder().id(1l).email("Test@test.com").build()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }


    @Test
    public void testAddToDoDetail() {
        toDoService.addToDoDetail(getTodoAddRequest());
        verify(todoRepository, times(1)).save(any());
    }

    @Test(expected = NoDataFoundException.class)
    public void testUpdateToDoDetailNoDataFoundException() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());
        toDoService.updateToDoDetail(getTodoUpdateRequest());
    }

    @Test
    public void testUpdateToDoDetail() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(getToDo()));
        toDoService.updateToDoDetail(getTodoUpdateRequest());
        verify(todoRepository, times(1)).save(any());
    }

    @Test
    public void testGetAllActiveToDoListEmpty() {
        when(todoRepository.findByUserIdAndDoneFalseOrderByTargetDateAsc(anyLong())).thenReturn(Collections.emptyList());
        List<TodoResponse> todoResponseList = toDoService.getAllActiveToDoList(1l);
        Assert.assertEquals(0, todoResponseList.size());
    }

    @Test
    public void testGetAllActiveToDoList() {
        when(todoRepository.findByUserIdAndDoneFalseOrderByTargetDateAsc(anyLong())).thenReturn(Arrays.asList(getToDo()));
        List<TodoResponse> todoResponseList = toDoService.getAllActiveToDoList(1l);
        Assert.assertEquals(1, todoResponseList.size());
    }

    @Test
    public void testDeleteById() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(getToDo()));
        toDoService.deleteById(1l);
        verify(todoRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = NoDataFoundException.class)
    public void testDeleteByIdNoDataFoundException() {
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());
        toDoService.deleteById(1l);
    }

    private Todo getToDo() {
        return Todo.builder()
                .done(true)
                .id(1l).userId(1l)
                .description("Test")
                .targetDate(LocalDateTime.now()).build();
    }

    private TodoUpdateRequest getTodoUpdateRequest() {
        return TodoUpdateRequest.builder().description("Test").targetDate(LocalDateTime.now()).id(1l).build();
    }

    private TodoAddRequest getTodoAddRequest() {
        return TodoAddRequest.builder().description("Test").targetDate(LocalDateTime.now()).build();
    }
}
