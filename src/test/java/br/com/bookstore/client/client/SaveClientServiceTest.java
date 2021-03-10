package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.SaveClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.bookstore.client.client.builders.ClientBuilder.createClient;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for save client")
class SaveClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private SaveClientServiceImpl saveClientService;

    @BeforeEach
    void setUp() {
        this.saveClientService = new SaveClientServiceImpl(clientRepositoryMock);
    }
    @Test
    @DisplayName("save returns client when successful")
    void saveReturnsClientWhenSuccessful() {

        Client client = createClient().build();

        saveClientService.insert(client);

        ArgumentCaptor<Client> captorClient = ArgumentCaptor.forClass(Client.class);
        verify(clientRepositoryMock, times(1)).save(captorClient.capture());

        Client result = captorClient.getValue();

        assertAll("Client",
                ()-> assertThat(result.getName(), is("Aktsuki")),
                () -> assertThat(result.getAge(), is(22)),
                () -> assertThat(result.getEmail(), is("teste@email")),
                () -> assertThat(result.getPhone(), is("teste-phone")),
                () -> assertThat(result.getSex(), is(Sex.MALE))
        );
    }
}
