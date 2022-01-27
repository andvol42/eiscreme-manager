package com.mtmd.eiscrememanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException() {
        super();
    }
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
    public DuplicateResourceException(String message) {
        super(message);
    }
    public DuplicateResourceException(Throwable cause) {
        super(cause);
    }
}
