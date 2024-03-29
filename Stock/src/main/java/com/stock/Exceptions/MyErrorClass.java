package com.stock.Exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyErrorClass {
    private LocalDateTime timeStamp;
    private String message;
    private String description;
}
