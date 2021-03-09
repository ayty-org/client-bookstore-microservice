package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;

@FunctionalInterface
public interface GetSpecificIdClientService {
    Client findBySpecificID(String specificID);
}
