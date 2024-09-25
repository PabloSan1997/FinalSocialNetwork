package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {
    private String error;
    private String message;
    private Integer statusCode;
    public ErrorDto(HttpStatus status, Exception e){
        this.error = status.getReasonPhrase();
        this.statusCode = status.value();
        this.message = e.getMessage();
    }
    public Date getTimestamp(){
        return new Date();
    }
}
