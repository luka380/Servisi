package com.project.prjx.Data.Model.Dto.Users;

import com.project.prjx.Data.Mappers.Mapper;
import com.project.prjx.Data.Model.Entity.Users.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class ClientDto extends BaseUserDto implements Mapper<ClientDto, Client> {
    @Builder
    public ClientDto(UUID id, String username, String password, EmailDto email, LocalDateTime birthday, String firstName, String lastName, Number phoneNumber, Boolean isEnabled) {
        super(id, username, password, email, birthday, firstName, lastName, phoneNumber, isEnabled, "ROLE_USER");
    }


    @Override
    public Client map(ClientDto object) {
        return null;
    }

    @Override
    public ClientDto reverseMap(Client object) {
        return null;
    }
}
