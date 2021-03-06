package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.GetClientServiceImpl;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade dos serviços responsáveis pela busca de um cliente por id")
class GetClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private GetClientServiceImpl getClientService;

    @BeforeEach
    void setUp() {
        this.getClientService = new GetClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("findById retorna cliente com sucesso")
    void findByIdReturnClientWhenSuccessful(){

        Client client = createClient().build(); //create a build to client
        Optional<Client> clientSavedOptional = Optional.of(client);
        when(clientRepositoryMock.findById(anyLong())).thenReturn(clientSavedOptional);

        Client result = this.getClientService.findById(1L); //result of requisition

        //verification
        assertAll("Client",
                () -> assertThat(result.getName(),is("Aktsuki")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getEmail(),is("teste@email")),
                () -> assertThat(result.getPhone(),is("teste-phone")),
                () -> assertThat(result.getSex(), is(Sex.MALE))
        );

        verify(clientRepositoryMock, times(1)).findById(1L);

    }

    @Test
    @DisplayName("findById lança ClientNotFoundException quando cliente não é encontrado")
    void findByIdClientThrowClientNotFoundExceptionWhenClientNotFound() {

        when(clientRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ClientNotFoundException.class, () -> getClientService.findById(1l));
        verify(clientRepositoryMock, times(1)).findById(anyLong());
    }
}