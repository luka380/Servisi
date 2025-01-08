package com.project.prjx.Services.ServiceImpl;

import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Repositories.ManagerRepository;
import com.project.prjx.Services.ServiceInterface.ManagerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerServiceInterface {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public ManagerDto getUserById(UUID id) {
        return null;
    }

    @Override
    public ManagerDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<ManagerDto> getAllUsers() {
        return List.of();
    }

    @Override
    public List<ManagerDto> getAllUsersUsernameContains(String filter) {
        return List.of();
    }

    @Override
    public void changePasswordByUsername() {

    }

    @Override
    public ManagerDto saveUser(ManagerDto u) {
        try {
//            return managerRepository.save(u);
            return null;
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Something is already taken");
        }
    }

    @Override
    public void deleteUser(UUID id) {

    }

    @Override
    public List<ManagerDto> getManagersByEmail(String email) {
        return List.of();
    }
}
