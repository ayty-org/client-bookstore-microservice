package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;

import java.util.UUID;

@FunctionalInterface
public interface GetSpecificIdClientService {
    Client findBySpecificID(String specificID);
}
