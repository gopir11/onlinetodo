package com.todo.app.service;

import com.todo.app.dto.TodoAddRequest;
import com.todo.app.dto.TodoResponse;
import com.todo.app.dto.TodoUpdateRequest;
import com.todo.app.exception.NoDataFoundException;

import java.util.List;

/**
 * Interface that defines the to-do operations contract
 */
public interface ToDoService {
    /**
     * This method validates the user and add if the user doesn't exist in our system
     * @param todoRequest To-Do add request
     * @return Added To-Do details
     */
    TodoResponse addToDoDetail(TodoAddRequest todoRequest);

    /**
     * This method validates the user and update To-Do details
     * @param todoRequest To-Do add request
     * @throws NoDataFoundException If To-Do details not present
     */
    void updateToDoDetail(TodoUpdateRequest todoRequest);

    /**
     * This api returns all the active To-Do lists sorted by target date
     * @param userId To-Do add/update request
     * @return list of active To-Do details of user
     */
    List<TodoResponse> getAllActiveToDoList(Long userId);

    /**
     * Deletes the To-Do details of the given id
     * @param toDoId To-Do add/update request
     * @throws NoDataFoundException If To-Do details not present
     */
    void deleteById(Long toDoId);
}
