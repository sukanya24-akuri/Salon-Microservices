package com.spring.exception;

import com.spring.payload.exceptionresponse.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
@RestControllerAdvice
public class ExceptionHandler
{

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
public ResponseEntity<ExceptionResponse> exceptionResponseResponseEntity(Exception ex, WebRequest req)
{
    ExceptionResponse response=new ExceptionResponse(
            ex.getMessage(),
            req.getDescription(false),
            LocalDateTime.now()
    );
    return ResponseEntity.ok(response);
}
}
