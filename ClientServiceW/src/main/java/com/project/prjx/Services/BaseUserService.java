package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Repositories.BaseUserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BaseUserService {
    private final BaseUserRepository userRepository;

    public BaseUserService(BaseUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BaseUserDto getUserByUsername(String username) {
        return Mappers.BaseUserMapper.map(userRepository.findByUsername(username));
    }

    public BaseUserDto getUserById(UUID id) {
        return Mappers.BaseUserMapper.map(userRepository.findById(id).orElse(null));
    }

    public void saveUser(BaseUserDto updated) {
        userRepository.save(Mappers.BaseUserMapper.reverseMap(updated));
    }
}
