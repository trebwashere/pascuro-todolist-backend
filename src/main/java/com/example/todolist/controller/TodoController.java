package com.example.todolist.controller;

import com.example.todolist.mapper.TodoMapper;
import com.example.todolist.model.entity.Todo;
import com.example.todolist.model.request.TodoRequest;
import com.example.todolist.model.response.TodoResponse;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/")
public class TodoController {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoMapper todoMapper;

    @GetMapping(path = "/todos")
    public List<TodoResponse> getAllTodos() {
        return todoMapper.toResponseList(todoService.getAllTodos());
    }

    @PutMapping(path = "/todos/{id}")
    public TodoResponse updateTodoStatus(@PathVariable String id, @RequestBody TodoRequest todoRequestUpdate) {
        return todoMapper.toResponse(todoService.updateToDoStatus(id, todoMapper.toEntity(todoRequestUpdate)));
    }

    @DeleteMapping(path = "/todos/{id}")
    public TodoResponse deleteTodo(@PathVariable String id) {
        return todoMapper.toResponse(todoService.deleteTodo(id));
    }

    @PostMapping(path = "/todos")
    public TodoResponse addTodo(@RequestBody TodoRequest todoRequest) {
        return todoMapper.toResponse(todoService.addTodo(todoMapper.toEntity(todoRequest)));
    }
}