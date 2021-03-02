package br.com.bookstore.client.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findBySpecificID(UUID specificID);
    boolean existsByEmailAndPhone(String email, String phone);
}
