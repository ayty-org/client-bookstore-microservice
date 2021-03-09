package br.com.bookstore.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientEmailOrPhoneExistsException extends RuntimeException {
    public ClientEmailOrPhoneExistsException(){
        super("Email or Phone Already Registered");
    }
}
