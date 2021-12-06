package com.todo.app.controller;

import com.todo.app.common.response.Response;
import com.todo.app.common.util.SecurityUtil;
import com.todo.app.config.security.UserPrincipal;
import com.todo.app.dto.TodoAddRequest;
import com.todo.app.dto.TodoResponse;
import com.todo.app.dto.TodoUpdateRequest;
import com.todo.app.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.todo.app.common.util.ResponseUtil.createdResponse;
import static com.todo.app.common.util.ResponseUtil.okResponse;

@Slf4j
@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @PostMapping
    @ApiOperation(value="Add new todo item", response = TodoResponse.class)
    public ResponseEntity<Response<TodoResponse>> addToDo(@ApiParam(value = "ToDo add request") @Validated @RequestBody TodoAddRequest todoAddRequest) {
        log.debug("Add todo task started");
        return createdResponse(toDoService.addToDoDetail(todoAddRequest));
    }

    @PutMapping
    @ApiOperation(value="Update todo item", response = HttpStatus.class)
    public ResponseEntity<Response<HttpStatus>> updateTodo(@ApiParam(value = "ToDo update request") @Validated @RequestBody TodoUpdateRequest todoUpdateRequest) {
        log.debug("Update todo task started : {}", todoUpdateRequest.getId());
        toDoService.updateToDoDetail(todoUpdateRequest);
        return createdResponse(HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value="Get all active todo list of the user", response = TodoResponse.class, responseContainer = "List")
    public ResponseEntity<Response<List<TodoResponse>>> getTodoList() {
        return okResponse(toDoService.getAllActiveToDoList(SecurityUtil.getAuthenticatedUserId()));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete todo item", response = HttpStatus.class)
    public ResponseEntity<Response<HttpStatus>> deleteTodo(@ApiParam("Id of the todo item which is to be deleted") @PathVariable(value = "id") Long id) {
        toDoService.deleteById(id);
        return okResponse(HttpStatus.OK);
    }
}
