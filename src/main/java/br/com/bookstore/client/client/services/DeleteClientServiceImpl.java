package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.ClientRepository;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        //TODO ver relação com a compra
        if(!clientRepository.existsById(id)){
            throw new ClientNotFoundException();
        }

        clientRepository.deleteById(id);
    }
}
