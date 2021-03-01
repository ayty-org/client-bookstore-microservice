package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientDTO;
import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateClientServiceImpl implements UpdateClientService {

    private final ClientRepository clientRepository;

    @Override
    public void update(ClientDTO clientDTO, Long id) {
        Client savedClient = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);

        savedClient.setName(clientDTO.getName());
        savedClient.setAge(clientDTO.getAge());
        savedClient.setEmail(clientDTO.getEmail());
        savedClient.setPhone(clientDTO.getPhone());
        savedClient.setSexo(clientDTO.getSexo());

        clientRepository.save(savedClient);
    }
}
