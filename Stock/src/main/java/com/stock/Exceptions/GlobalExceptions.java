package com.stock.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(TradeException.class)
    public ResponseEntity<MyErrorClass> applicationException(TradeException te, WebRequest webRequest){
        MyErrorClass myErrorClass = new MyErrorClass();
        myErrorClass.setMessage(te.getLocalizedMessage());
        myErrorClass.setTimeStamp(LocalDateTime.now());
        myErrorClass.setDescription(webRequest.getDescription(false));

        return new ResponseEntity<>(myErrorClass, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorClass> exception(Exception e, WebRequest wr){
        MyErrorClass myErrorClass = new MyErrorClass();
        myErrorClass.setDescription(wr.getDescription(false));
        myErrorClass.setMessage(e.getMessage());
        myErrorClass.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(myErrorClass, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorClass> noHandlerFoundException(NoHandlerFoundException nfe, WebRequest wr){
        MyErrorClass myErrorClass = new MyErrorClass();
        myErrorClass.setTimeStamp(LocalDateTime.now());
        myErrorClass.setDescription(wr.getDescription(false));
        myErrorClass.setMessage(nfe.getMessage());

        return new ResponseEntity<>(myErrorClass, HttpStatus.BAD_GATEWAY);
    }

}

