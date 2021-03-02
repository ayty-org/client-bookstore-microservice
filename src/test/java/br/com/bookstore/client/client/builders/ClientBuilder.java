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
                .specificID("d2dbaa68-48c6-451e-b34f-57b5b70fc0ed")
                .phone("teste-phone")
                .sexo(Sex.MASCULINO);
    }
}