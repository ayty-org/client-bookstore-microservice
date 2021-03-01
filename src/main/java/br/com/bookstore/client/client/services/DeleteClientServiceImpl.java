package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteClientServiceImpl implements DeleteClientService{

    private final ClientRepository clientRepository;

    @Override
    public void delete(Long id) {
        if(!clientRepository.existsById(id)){
            throw new RuntimeException();
        }

        clientRepository.deleteById(id);
    }
}
