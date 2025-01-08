package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByUsername(String username);
    List<Client> findAllById(UUID id);
    List<Client> findAllByEmail_Email(String email);
}
