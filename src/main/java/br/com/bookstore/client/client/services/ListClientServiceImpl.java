package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListClientServiceImpl implements ListClientService{

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
