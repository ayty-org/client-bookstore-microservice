package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;

    @Override
    public void insert(Client client) {
        if(clientRepository.existsByEmailAndPhone(client.getEmail(), client.getPhone())){
            throw new ClientAlreadyExistsException();
        }
        clientRepository.save(client);
    }
}
