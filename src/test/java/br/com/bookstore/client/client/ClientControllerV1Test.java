package br.com.bookstore.client.client;

import br.com.bookstore.client.client.services.DeleteClientService;
import br.com.bookstore.client.client.services.GetClientService;
import br.com.bookstore.client.client.services.GetSpecificIdClientService;
import br.com.bookstore.client.client.services.ListClientService;
import br.com.bookstore.client.client.services.ListPageClientService;
import br.com.bookstore.client.client.services.SaveClientService;
import br.com.bookstore.client.client.services.UpdateClientService;
import br.com.bookstore.client.client.v1.ClientControllerV1;
import br.com.bookstore.client.exceptions.ClientNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static br.com.bookstore.client.client.builders.ClientBuilder.createClient;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(ClientControllerV1.class)
@DisplayName("Valida a funcionalidade do controlador responsável pelo cliente")
class ClientControllerV1Test {

    private final String URL_CLIENT = "/v1/api/client";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetClientService getClientService;

    @MockBean
    private GetSpecificIdClientService getSpecificIdClientService;

    @MockBean
    private ListClientService listClientAppService;

    @MockBean
    private ListPageClientService listPageClientService;

    @MockBean
    private UpdateClientService updateClientService;

    @MockBean
    private DeleteClientService deleteClientService;

    @MockBean
    private SaveClientService saveClientService;

    @Test
    @DisplayName("findById retorna o cliente quando bem-sucedido")
    void findByIdReturnClientWhenSuccessful() throws Exception{

        when(getClientService.findById(anyLong())).thenReturn(createClient().build());

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Aktsuki")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.email", is("teste@email")))
                .andExpect(jsonPath("$.phone", is("teste-phone")))
                .andExpect(jsonPath("$.sex", is("MALE")));

        verify(getClientService).findById(anyLong());
    }

    @Test
    @DisplayName("findById lança ClientNotFoundException quando o cliente não é encontrado")
    void findByIdClientThrowClientNotFoundExceptionWhenClientNotFound() throws Exception {

        when(getClientService.findById(anyLong())).thenThrow(new ClientNotFoundException());

        mockMvc.perform(get(URL_CLIENT + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getClientService).findById(1L);
    }

    @Test
    @DisplayName("findBySpecificId retorna o cliente quando bem-sucedido")
    void findBySpecificIDReturnClientWhenSuccessful() throws Exception{

        when(getSpecificIdClientService.findBySpecificID(anyString())).thenReturn(createClient().build());

        mockMvc.perform(get(URL_CLIENT + "/id/{specificID}", "d2dbaa68-48c6-451e-b34f-57b5b70fc0ed").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Aktsuki")))
                .andExpect(jsonPath("$.age", is(22)))
                .andExpect(jsonPath("$.email", is("teste@email")))
                .andExpect(jsonPath("$.phone", is("teste-phone")))
                .andExpect(jsonPath("$.sex", is("MALE")));

        verify(getSpecificIdClientService).findBySpecificID(anyString());
    }

    @Test
    @DisplayName("listAll retorna a lista de clientes quando bem-sucedido")
    void listAllReturnsListOfClientsWhenSuccessfull() throws Exception {

        when(listClientAppService.findAll()).thenReturn(Lists.newArrayList(
                createClient().id(1L).build(),
                createClient().id(2L).build()
        ));

        mockMvc.perform(get(URL_CLIENT).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Aktsuki")))
                .andExpect(jsonPath("$[0].age", is(22)))
                .andExpect(jsonPath("$[0].email",is("teste@email")))
                .andExpect(jsonPath("$[0].phone",  is("teste-phone")))
                .andExpect(jsonPath("$[0].sex", is("MALE")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Aktsuki")))
                .andExpect(jsonPath("$[1].age",  is(22)))
                .andExpect(jsonPath("$[1].email", is("teste@email")))
                .andExpect(jsonPath("$[1].phone", is("teste-phone")))
                .andExpect(jsonPath("$[1].sex", is("MALE")));


        verify(listClientAppService).findAll();
    }

    @Test
    @DisplayName("listAll retorna a lista do cliente dentro do objeto da página quando bem-sucedido")
    void listAllReturnsListOfClientInsidePageObject_WhenSuccessful() throws Exception{

        Page<Client> clientPage = new PageImpl<>(Collections.singletonList(createClient().id(1L).build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageClientService.findPage(pageable)).thenReturn(clientPage);

        mockMvc.perform(get(URL_CLIENT + "/?page=0&size=2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id",   is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Aktsuki")))
                .andExpect(jsonPath("$.content[0].age",  is(22)))
                .andExpect(jsonPath("$.content[0].email",is("teste@email")))
                .andExpect(jsonPath("$.content[0].phone",is("teste-phone")))
                .andExpect(jsonPath("$.content[0].sex", is("MALE")));

        verify(listPageClientService).findPage(pageable);
    }

    @Test
    @DisplayName("save retorna o cliente quando bem-sucedido")
    void saveReturnsClientWhenSuccessful() throws Exception{

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(saveClientService).insert(any(Client.class));
    }

    @Test
    @DisplayName("save lança um cliente quando o nome está vazio")
    void saveThrowBadRequestWhenNameIsEmpty() throws Exception{

        Client client = createClient().id(1L).name("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save lança cliente quando o telefone está vazio")
    void saveThrowBadRequestWhenPhoneIsEmpty() throws Exception{

        Client client = createClient().id(1L).phone("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("save lança o cliente quando o e-mail está vazio")
    void saveThrowBadRequestWhenEmailIsEmpty() throws Exception{

        Client client = createClient().id(1L).email("").build();

        mockMvc.perform(post(URL_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("update o cliente quando for bem sucedido")
    void updateReturnsClientUpdateWhenSuccessful() throws Exception{

        mockMvc.perform(put(URL_CLIENT + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("clientUpdate.json")))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updateClientService).update(any(ClientDTO.class), eq(1L));
    }

    @Test
    @DisplayName("delete remover clientes quando bem-sucedido")
    void deleteRemoveClientWhenSuccessful() throws Exception{
        mockMvc.perform(delete(URL_CLIENT + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deleteClientService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
        return new String(bytes);
    }
}
