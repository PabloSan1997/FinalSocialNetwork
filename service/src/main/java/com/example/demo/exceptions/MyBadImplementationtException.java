package com.example.demo.exceptions;

public class MyBadImplementationtException extends RuntimeException{
    public MyBadImplementationtException() {
    }

    public MyBadImplementationtException(String message) {
        super(message);
    }
}
