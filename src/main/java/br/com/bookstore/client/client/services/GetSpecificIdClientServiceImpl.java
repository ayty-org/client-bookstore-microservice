package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GetSpecificIdClientServiceImpl implements GetSpecificIdClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client findBySpecificID(UUID specificID) {
        return clientRepository.findBySpecificID(specificID).orElseThrow(ClientNotFoundException::new);
    }
}
