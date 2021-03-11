package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.ListPageClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static br.com.bookstore.client.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade dos serviços responsáveis pela paginação de todos os clientes")
class ListPageClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ListPageClientServiceImpl listPageClientService;

    @BeforeEach
    void setUp() {
        this.listPageClientService = new ListPageClientServiceImpl(clientRepository);
    }

    @Test
    @DisplayName("listAll retorna a lista do cliente dentro do objeto da página quando bem-sucedido")
    void listAllReturnsListOfClientInsidePageObjectWhenSuccessful() {

        Pageable pageable = PageRequest.of(0, 2);

        when(listPageClientService.findPage(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createClient().build())));

        Page<Client> result = listPageClientService.findPage(pageable);

        assertAll("Client",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(2L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(2)),
                ()-> assertThat(result.getContent().get(0).getName(), is("Aktsuki")),
                () -> assertThat(result.getContent().get(0).getAge(), is(22)),
                () -> assertThat(result.getContent().get(0).getEmail(), is("teste@email")),
                () -> assertThat(result.getContent().get(0).getPhone(), is("teste-phone")),
                () -> assertThat(result.getContent().get(0).getSex(), is(Sex.MALE)),

                ()-> assertThat(result.getContent().get(1).getName(), is("Aktsuki")),
                () -> assertThat(result.getContent().get(1).getAge(), is(22)),
                () -> assertThat(result.getContent().get(1).getEmail(), is("teste@email")),
                () -> assertThat(result.getContent().get(1).getPhone(), is("teste-phone")),
                () -> assertThat(result.getContent().get(1).getSex(), is(Sex.MALE))
        );
    }
}