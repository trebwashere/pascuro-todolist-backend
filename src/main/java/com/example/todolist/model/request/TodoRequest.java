package com.example.todolist.model.request;

public class TodoRequest {

    String text;
    Boolean done;

    public TodoRequest() {
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}