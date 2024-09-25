package com.example.demo.exceptions;

public class MyNotFoundException extends RuntimeException{
    public MyNotFoundException() {
    }

    public MyNotFoundException(String message) {
        super(message);
    }
}
