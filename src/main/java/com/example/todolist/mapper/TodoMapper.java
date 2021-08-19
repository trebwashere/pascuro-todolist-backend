package com.example.todolist.mapper;

import com.example.todolist.model.entity.Todo;
import com.example.todolist.model.request.TodoRequest;
import com.example.todolist.model.response.TodoResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoRequest, todo);
        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }

    public List<TodoResponse> toResponseList(List<Todo> todos) {
        return todos.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
