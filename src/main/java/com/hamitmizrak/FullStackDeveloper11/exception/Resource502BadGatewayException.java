package com.hamitmizrak.FullStackDeveloper11.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//502: Kötü ağ Trafi (Bad Gateway)
@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class Resource502BadGatewayException extends RuntimeException{

    // Override
    public Resource502BadGatewayException(String message) {
        super(message);
    }
}
