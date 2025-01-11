package com.project.prjx.Data.Model.Dto.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ManagerDto extends BaseUserDto {
    private LocalDateTime hiringDate;

    @Builder
    public ManagerDto(UUID id, String username, String password, EmailDto email, LocalDateTime birthday, String firstName, String lastName, Boolean isEnabled, LocalDateTime hiringDate) {
        super(id, username, password, email, birthday, firstName, lastName, isEnabled, "ROLE_MANAGER");
        this.hiringDate = hiringDate;
    }
}
