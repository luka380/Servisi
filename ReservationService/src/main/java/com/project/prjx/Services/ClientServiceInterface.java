package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Users.ClientDto;

import java.util.List;

public interface ClientServiceInterface extends BaseClientServiceInterface<ClientDto> {
    List<ClientDto> getClientsByEmail(String email);
}
