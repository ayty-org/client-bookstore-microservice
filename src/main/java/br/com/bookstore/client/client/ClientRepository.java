package br.com.bookstore.client.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findBySpecificID(String specificID);
    boolean existsByEmailOrPhone(String email, String phone);
}
