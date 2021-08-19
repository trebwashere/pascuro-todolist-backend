package com.example.todolist.exception;

public class TodoNotFoundException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Todo not found!";
    }
}
