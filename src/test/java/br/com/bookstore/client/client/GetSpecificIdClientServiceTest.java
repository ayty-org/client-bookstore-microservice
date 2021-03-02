package br.com.bookstore.client.client;


import br.com.bookstore.client.client.services.GetSpecificIdClientService;
import br.com.bookstore.client.client.services.GetSpecificIdClientServiceImpl;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.client.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for a client by specificID ")
class GetSpecificIdClientServiceTest {
    @Mock
    private ClientRepository clientRepositoryMock;

    private GetSpecificIdClientService getSpecificIdClientService;

    @BeforeEach
    void setUp() {
        this.getSpecificIdClientService = new GetSpecificIdClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("findById returns client when succesful")
    void findByIdReturnClientWhenSuccessful(){

        Client client = createClient().build(); //create a build to client
        Optional<Client> clientSavedOptional = Optional.of(client);
        when(clientRepositoryMock.findBySpecificID(anyString())).thenReturn(clientSavedOptional);

        Client result = this.getSpecificIdClientService.findBySpecificID("d2dbaa68-48c6-451e-b34f-57b5b70fc0ed"); //result of requisition

        //verification
        assertAll("Client",
                () -> assertThat(result.getName(),is("Aktsuki")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getEmail(),is("teste@email")),
                () -> assertThat(result.getPhone(),is("teste-phone")),
                () -> assertThat(result.getSexo(), is(Sex.MASCULINO))
        );

        verify(clientRepositoryMock, times(1)).findBySpecificID("d2dbaa68-48c6-451e-b34f-57b5b70fc0ed");

    }

    @Test
    @DisplayName("findById throws ClientNotFoundException when client is not found")
    void findByIdClientThrowClientNotFoundExceptionWhenClientNotFound() {

        when(clientRepositoryMock.findBySpecificID(anyString())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> getSpecificIdClientService.findBySpecificID("dsadasdasdasda"));
        verify(clientRepositoryMock, times(1)).findBySpecificID(anyString());
    }
}