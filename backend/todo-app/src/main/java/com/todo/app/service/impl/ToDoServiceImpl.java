package com.todo.app.service.impl;

import com.todo.app.common.util.SecurityUtil;
import com.todo.app.dto.TodoAddRequest;
import com.todo.app.dto.TodoResponse;
import com.todo.app.dto.TodoUpdateRequest;
import com.todo.app.exception.NoDataFoundException;
import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
import com.todo.app.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ToDoServiceImpl implements ToDoService {

    private final TodoRepository todoRepository;

    @Autowired
    public ToDoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
    /**
     * This method validates the user and add if the user doesn't exist in our system
     *
     * @param todoRequest To-Do add request
     * @return Added To-Do details
     */
    @Transactional
    @Override
    public TodoResponse addToDoDetail(TodoAddRequest todoRequest) {
        log.debug("Add todo request");
        LocalDateTime currentDateTime = LocalDateTime.now();
        Todo toDo = Todo.builder()
                .userId(SecurityUtil.getAuthenticatedUserId())
                .description(todoRequest.getDescription())
                .targetDate(todoRequest.getTargetDate())
                .done(false)
                .createdDate(currentDateTime)
                .modifiedDate(currentDateTime).build();
        todoRepository.save(toDo);

        return getTodoResponse(toDo);
    }

    private TodoResponse getTodoResponse(Todo toDo) {
        return TodoResponse.builder()
                .id(toDo.getId())
                .description(toDo.getDescription())
                .targetDate(toDo.getTargetDate())
                .done(toDo.isDone()).build();
    }

    /**
     * This method validates the user and update To-Do details
     *
     * @param todoUpdateRequest To-Do add request
     * @throws NoDataFoundException If To-Do details not present
     */
    @Transactional
    @Override
    public void updateToDoDetail(TodoUpdateRequest todoUpdateRequest) {
        log.debug("Update todo details");
        Todo toDo = todoRepository.findById(todoUpdateRequest.getId()).orElseThrow(() -> new NoDataFoundException("Todo detail is missing"));

        toDo.setDescription(todoUpdateRequest.getDescription());
        toDo.setDone(todoUpdateRequest.isDone());
        toDo.setModifiedDate(LocalDateTime.now());
        todoRepository.save(toDo);
        log.debug("Updated todo details");
    }

    /**
     * This api returns all the active To-Do lists sorted by target date
     *
     * @param userId User Identified
     * @return list of all active To-Do details of user
     */
    @Transactional
    @Override
    public List<TodoResponse> getAllActiveToDoList(Long userId) {
        return todoRepository.findByUserIdAndDoneFalseOrderByTargetDateAsc(userId)
                .stream()
                .map(this::getTodoResponse).collect(Collectors.toList());
    }

    /**
     * Deletes the To-Do details of the given id
     *
     * @param toDoId To-Do add/update request
     * @throws NoDataFoundException If To-Do details not present
     */
    @Transactional
    @Override
    public void deleteById(Long toDoId) {
        todoRepository.findById(toDoId).orElseThrow(() ->  new NoDataFoundException("Todo detail is missing"));
        todoRepository.deleteById(toDoId);
    }
}
