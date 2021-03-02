package br.com.bookstore.client.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    private String phone;

    private String email;

    private Sex sexo;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "specific_id")
    private String specificID = UUID.randomUUID().toString();

    public static Client to(ClientDTO dto) {
        return Client
                .builder()
                .name(dto.getName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .sexo(dto.getSexo())
                .specificID(dto.getSpecificID())
                .build();
    }
}
