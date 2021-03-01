package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;

import java.util.List;

public interface ListClientService {
    List<Client> findAll();
}
