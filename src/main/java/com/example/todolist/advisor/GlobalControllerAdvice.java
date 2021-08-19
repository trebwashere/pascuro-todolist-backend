package com.example.todolist.advisor;

import com.example.todolist.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoNotFoundException.class)
    public ErrorResponse handleEmployeeNotFoundException(TodoNotFoundException e) {
        return new ErrorResponse(e.getMessage(), printStackTrace(e));
    }

    private String printStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString().substring(0, 300);
    }
}
