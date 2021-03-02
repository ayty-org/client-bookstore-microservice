package br.com.bookstore.client.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "The client name cannot be empty")
    private String name;

    @NotNull(message = "The client age cannot be null")
    @Min(value = 2)
    private Integer age;

    @NotEmpty(message = "The client phone cannot be empty")
    private String phone;

    @NotEmpty(message = "The client email cannot be empty")
    @Email
    private String email;

    @NotNull(message = "The client sexo cannot be null")
    @Enumerated(EnumType.STRING)
    private Sex sexo;

    private String specificID = UUID.randomUUID().toString();

    public static ClientDTO from(Client entity) {
        return ClientDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .sexo(entity.getSexo())
                .specificID(entity.getSpecificID())
                .build();
    }

    public static List<ClientDTO> fromAll(List<Client> clients) {
        return clients.stream().map(ClientDTO::from).collect(Collectors.toList());
    }

    public static Page<ClientDTO> fromPage(Page<Client> pages){
        return pages.map(ClientDTO::from);
    }
}
