package com.example.todolist.controller;

import com.example.todolist.mapper.TodoMapper;
import com.example.todolist.model.entity.Todo;
import com.example.todolist.model.request.TodoRequest;
import com.example.todolist.model.response.TodoResponse;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoMapper todoMapper;

    @GetMapping()
    public List<TodoResponse> getAllTodos() {
        return todoMapper.toResponseList(todoService.getAllTodos());
    }

    @PutMapping(path = "/{id}")
    public TodoResponse updateTodoStatus(@PathVariable String id, @RequestBody TodoRequest todoRequestUpdate) {
        return todoMapper.toResponse(todoService.updateToDoStatus(id, todoMapper.toEntity(todoRequestUpdate)));
    }

    @DeleteMapping(path = "/{id}")
    public TodoResponse deleteTodo(@PathVariable String id) {
        return todoMapper.toResponse(todoService.deleteTodo(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todoService.addTodo(todoMapper.toEntity(todoRequest)));
    }
}