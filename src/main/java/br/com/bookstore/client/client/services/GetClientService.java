package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;

@FunctionalInterface
public interface GetClientService {
    Client findById(Long id);
}
