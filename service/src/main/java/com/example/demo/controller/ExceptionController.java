package com.example.demo.controller;

import com.example.demo.exceptions.MyBadImplementationtException;
import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.models.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({
            NoResourceFoundException.class
    })
    public ResponseEntity<?> notFound(Exception e){
        return generateError(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validException(MethodArgumentNotValidException e){
        List<FieldError> errors = e.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        for(FieldError err:errors){
            stringBuilder.append(err.getField()).append(" ").append(err.getDefaultMessage()).append(". ");
        }
        String message = stringBuilder.toString();
        return badRequest(new MyBadRequestException(message));
    }

    @ExceptionHandler({
            MyBadRequestException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<?> badRequest(Exception e){
        return generateError(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler({
            MyBadImplementationtException.class
    })
    public ResponseEntity<?> badImplementation(Exception e){
        return generateError(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> notSupported(HttpRequestMethodNotSupportedException e){
        return generateError(HttpStatus.METHOD_NOT_ALLOWED, e);
    }

    private ResponseEntity<ErrorDto> generateError(HttpStatus status, Exception e){
        ErrorDto errorDto = new ErrorDto(status, e);
        return ResponseEntity.status(status.value()).body(errorDto);
    }
}
