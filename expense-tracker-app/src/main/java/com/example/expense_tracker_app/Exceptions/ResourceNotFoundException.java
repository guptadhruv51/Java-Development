package com.example.expense_tracker_app.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException
{
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
