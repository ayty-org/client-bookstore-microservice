package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.UpdateClientServiceImpl;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.client.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for update client")
class UpdateClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private UpdateClientServiceImpl updateClientService;

    @BeforeEach
    void setUp() {
        this.updateClientService = new UpdateClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("update client when successful")
    void updateReturnsClientUpdateWhenSuccessful(){

        Client putClientRequest = createClient()
                .name("Name update")
                .age(30)
                .build();

        Client client = createClient().build();
        Optional<Client> clientOptional  = Optional.of(client);
        when(clientRepositoryMock.findById(anyLong())).thenReturn(clientOptional);

        updateClientService.update(ClientDTO.from(putClientRequest), 1L);

        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepositoryMock).save(clientArgumentCaptor.capture());

        Client result = clientArgumentCaptor.getValue();

        assertAll("Client",
                ()-> assertThat(result.getName(), is("Name update")),
                () -> assertThat(result.getAge(), is(30)),
                () -> assertThat(result.getEmail(), is("teste@email")),
                () -> assertThat(result.getPhone(), is("teste-phone")),
                () -> assertThat(result.getSexo(), is(Sex.MASCULINO))
        );
    }

    @Test
    @DisplayName("update throws ClientNotFoundException when client is not found")
    void updateThrowClientNotFoundExceptionWhenClientNotFound() {
        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class,()-> this.updateClientService.update(ClientDTO.builder().build(), 1L));

        verify(clientRepositoryMock, times(0)).save(any());
    }
}
