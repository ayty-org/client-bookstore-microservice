package br.com.bookstore.client.client.services;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ListPageClientServiceImpl implements ListPageClientService {

    private final ClientRepository clientRepository;

    @Override
    public Page<Client> findPage(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }
}
