package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;

@FunctionalInterface
public interface SaveClientService {
    void insert(Client client);
}
