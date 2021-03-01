package br.com.bookstore.client.client.builders;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.Sex;

import java.util.UUID;

public class ClientBuilder {

    public static Client.Builder createClient() {
        return Client
                .builder()
                .id(1L)
                .name("Aktsuki")
                .age(22)
                .email("teste@email")
                .specificID(UUID.randomUUID())
                .phone("teste-phone")
                .sexo(Sex.MASCULINO);
    }
}