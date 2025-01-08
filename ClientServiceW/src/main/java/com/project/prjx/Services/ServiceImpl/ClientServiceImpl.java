package com.project.prjx.Services.ServiceImpl;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Entity.Users.Client;
import com.project.prjx.Data.Model.Entity.Users.Email;
import com.project.prjx.Data.Repositories.ClientRepository;
import com.project.prjx.Data.Repositories.EmailsRepository;
import com.project.prjx.Services.ServiceInterface.ClientServiceInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientServiceInterface {
    private final ClientRepository clientRepository;
    private final EmailsRepository emailsRepository;

    public ClientServiceImpl(ClientRepository clientRepository, EmailsRepository emailsRepository) {
        this.clientRepository = clientRepository;
        this.emailsRepository = emailsRepository;
    }

    @Override
    public ClientDto getUserById(UUID id) {
        return null;
    }

    @Override
    public ClientDto getUserByUsername(String username) {
        return Mappers.ClientMapper.map(clientRepository.findByUsername(username));
    }

    @Override
    public List<ClientDto> getClientsByEmail(String email){
        return Mappers.ClientMapper.map(clientRepository.findAllByEmail_Email(email));
    }

    @Override
    public List<ClientDto> getAllUsers() {
        return List.of();
    }

    @Override
    public List<ClientDto> getAllUsersUsernameContains(String filter) {
        return List.of();
    }

    @Override
    public void changePasswordByUsername() {

    }

    @Transactional
    @Override
    public ClientDto saveUser(ClientDto u) {
        try {
            Email ems = Mappers.EmailMapper.map(u.getEmail());

            Client client = Client.builder()
                    .birthday(u.getBirthday())
                    .firstName(u.getFirstName())
                    .lastName(u.getLastName())
                    .role(u.getRole())
                    .username(u.getUsername())
                    .isEnabled(u.getIsEnabled())
                    .password(u.getPassword())
                    .id(u.getId())
                    .phoneNumber(u.getPhoneNumber())
                    .build();

            Client newClient = clientRepository.save(client);

            newClient.setEmail(ems);

            return Mappers.ClientMapper.map(newClient);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Something is already taken");
        }
    }

    @Override
    public void deleteUser(UUID id) {
    }

}
