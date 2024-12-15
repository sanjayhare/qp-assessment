package com.grocery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class GroceryMessegeException extends RuntimeException {
    public GroceryMessegeException(String message) {
        super(message);
    }
}

