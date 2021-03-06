package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientEmailOrPhoneExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SaveClientServiceImpl implements SaveClientService {

    private final ClientRepository clientRepository;

    @Override
    public void insert(Client client) {
        if(clientRepository.existsByEmailOrPhone(client.getEmail(), client.getPhone())){
            throw new ClientEmailOrPhoneExistsException();
        }

        client.setSpecificID(UUID.randomUUID().toString());
        clientRepository.save(client);
    }
}
