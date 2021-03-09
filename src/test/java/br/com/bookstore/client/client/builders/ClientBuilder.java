package br.com.bookstore.client.client.builders;

import br.com.bookstore.client.client.Client;
import br.com.bookstore.client.client.Sex;

public class ClientBuilder {
    public static Client.Builder createClient() {
        return Client
                .builder()
                .id(1L)
                .name("Aktsuki")
                .age(22)
                .email("teste@email")
                .phone("teste-phone")
                .sex(Sex.MALE);
    }
}