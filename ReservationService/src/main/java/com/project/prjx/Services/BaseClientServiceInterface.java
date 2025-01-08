package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;

import java.util.List;
import java.util.UUID;

public interface BaseClientServiceInterface<T extends BaseUserDto> {
    T getUserById(UUID id);

    T getUserByUsername(String username);

    List<T> getAllUsers();

    List<T> getAllUsersUsernameContains(String filter);

    void changePasswordByUsername();

    T saveUser(T u);

    void deleteUser(UUID id);
}
