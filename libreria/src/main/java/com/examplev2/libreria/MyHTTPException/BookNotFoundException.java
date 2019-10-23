package com.examplev2.libreria.MyHTTPException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception {

    public BookNotFoundException() {
        super("BookNotFound");
    }
}
