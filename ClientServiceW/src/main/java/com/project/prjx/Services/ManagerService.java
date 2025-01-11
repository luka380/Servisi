package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Entity.Users.Email;
import com.project.prjx.Data.Model.Entity.Users.Manager;
import com.project.prjx.Data.Repositories.BaseUserRepository;
import com.project.prjx.Data.Repositories.EmailsRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ManagerService {
    private final BaseUserRepository userRepository;
    private final EmailsRepository emailsRepository;

    public ManagerService(BaseUserRepository userRepository, EmailsRepository emailsRepository) {
        this.userRepository = userRepository;
        this.emailsRepository = emailsRepository;
    }

    public ManagerDto getUserByUsername(String username) {
        return Mappers.ManagerMapper.reverseMap((Manager) userRepository.findByUsername(username));
    }


    public ManagerDto saveUser(ManagerDto u) {
        try {
            u.setRole("ROLE_MANAGER");
            Manager manager = userRepository.save(Mappers.ManagerMapper.map(u));
            Email e = new Email(u.getEmail().getEmail(), u.getEmail().isVerified(), manager);
            Email em = emailsRepository.save(e);
            manager.setEmail(em);
            return Mappers.ManagerMapper.reverseMap(manager);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Something is already taken");
        }
    }
}
