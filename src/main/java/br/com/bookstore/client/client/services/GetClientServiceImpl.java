package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetClientServiceImpl implements GetClientService{

    private final ClientRepository clientRepository;
    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }
}
