package com.project.prjx.Services.ServiceImpl;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Repositories.ClientRepository;
import com.project.prjx.Services.ServiceInterface.BaseClientServiceInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BaseClientServiceImpl implements BaseClientServiceInterface<BaseUserDto> {
    private ClientRepository clientRepository;

    @Override
    public BaseUserDto getUserById(UUID id) {
        return null;
    }

    @Override
    public BaseUserDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<BaseUserDto> getAllUsers() {
        return List.of();
    }

    @Override
    public List<BaseUserDto> getAllUsersUsernameContains(String filter) {
        return List.of();
    }

    @Override
    public void changePasswordByUsername() {

    }

    @Override
    public BaseUserDto saveUser(BaseUserDto u) {
        return null;
    }

    @Override
    public void deleteUser(UUID id) {

    }
}
