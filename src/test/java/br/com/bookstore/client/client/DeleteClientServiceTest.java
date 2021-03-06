package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.DeleteClientServiceImpl;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Valida a funcionalidade dos serviços responsáveis pela exclusão do cliente")
class DeleteClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    private DeleteClientServiceImpl deleteClientService;

    @BeforeEach
    void setUp() {
        this.deleteClientService = new DeleteClientServiceImpl(clientRepositoryMock);
    }

    @Test
    @DisplayName("delete, remover clientes quando bem-sucedido")
    void deleteRemoveClientWhenSuccessful() {

        when(clientRepositoryMock.existsById(anyLong())).thenReturn(true);
        deleteClientService.delete(1L);
        verify(clientRepositoryMock).existsById(anyLong());
        verify(clientRepositoryMock).deleteById(anyLong());
    }

    @Test
    @DisplayName("delete, lança ClientNotFoundException quando o cliente não é encontrado")
    void deleteThrowClientNotFoundExceptionWhenClientNotFound() {

        when(clientRepositoryMock.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(ClientNotFoundException.class, ()-> deleteClientService.delete(1L));

        verify(clientRepositoryMock, times(0)).deleteById(anyLong());
        verify(clientRepositoryMock).existsById(anyLong());
    }
}