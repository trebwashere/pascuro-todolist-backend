package com.example.todolist.service;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.entity.Todo;
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
        Todo todo = todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        if (todoUpdate.getDone() != null) {
            todo.setDone(todoUpdate.getDone());
        }
        if (todoUpdate.getText() != null) {
            todo.setText(todoUpdate.getText());
        }
        return todoRepository.save(todo);
    }

    public Todo deleteTodo(String id) {
        Todo toBeRemoved = todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
        todoRepository.deleteById(id);
        return toBeRemoved;
    }

    public Todo addTodo(Todo todoToAdd) {
        return todoRepository.save(todoToAdd);
    }
}
