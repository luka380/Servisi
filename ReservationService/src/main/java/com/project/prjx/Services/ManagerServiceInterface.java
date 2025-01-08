package com.project.prjx.Services;

import com.project.prjx.Data.Model.Dto.Users.ManagerDto;

import java.util.List;

public interface ManagerServiceInterface extends BaseClientServiceInterface<ManagerDto> {
    List<ManagerDto> getManagersByEmail(String email);
}
