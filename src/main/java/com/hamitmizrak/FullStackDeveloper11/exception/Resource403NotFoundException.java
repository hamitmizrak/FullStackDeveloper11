package com.hamitmizrak.FullStackDeveloper11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//404: Bulunamadı (Not Found)
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Resource403NotFoundException extends RuntimeException{

    // Override
    public Resource403NotFoundException(String message) {
        super(message);
    }
}
