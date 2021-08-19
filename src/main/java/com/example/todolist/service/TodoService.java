package com.example.todolist.service;

import com.example.todolist.model.entity.Todo;
import com.example.todolist.model.request.TodoRequest;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoService {
    @Resource
    TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo updateToDoStatus(String id, Todo todoUpdate) {
        Todo todo = todoRepository.findById(id).orElse(null);
        todo.setDone(todoUpdate.getDone());
        return todoRepository.save(todo);
    }
}
