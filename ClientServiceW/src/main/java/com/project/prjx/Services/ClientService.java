package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Entity.Users.Email;
import com.project.prjx.Data.Repositories.BaseUserRepository;
import com.project.prjx.Data.Repositories.EmailsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private final BaseUserRepository userRepository;
    private final EmailsRepository emailsRepository;

    public ClientService(BaseUserRepository userRepository, EmailsRepository emailsRepository) {
        this.userRepository = userRepository;
        this.emailsRepository = emailsRepository;
    }


    public ClientDto getUserByUsername(String username) {
        return Mappers.ClientMapper.map((Client) userRepository.findByUsername(username));
    }

    @Transactional
    public ClientDto saveUser(ClientDto u) {
        try {
            u.setRole("ROLE_CLIENT");
            Client c = userRepository.save(Mappers.ClientMapper.reverseMap(u));
            Email e = new Email(u.getEmail().getEmail(), u.getEmail().isVerified(), c);
            emailsRepository.save(e);
            c.setEmail(e);
            return Mappers.ClientMapper.map(c);

        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Something is already taken");
        }
    }
}
