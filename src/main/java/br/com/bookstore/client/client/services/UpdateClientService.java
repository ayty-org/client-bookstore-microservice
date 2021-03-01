package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.ClientDTO;

@FunctionalInterface
public interface UpdateClientService {
    void update(ClientDTO client, Long id);
}
