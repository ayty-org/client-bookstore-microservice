package br.com.bookstore.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(){
        super("Client already exists");
    }
}
