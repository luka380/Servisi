package com.project.prjx.Data.Model.Entity.Users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@DiscriminatorValue("MANAGER")
public class Manager extends BaseUser {
    @NotNull
    private LocalDateTime hiringDate;

    @Builder
    public Manager(UUID id, String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Boolean isEnabled, String role, LocalDateTime hiringDate) {
        super(id, username, password, email, birthday, firstName, lastName, isEnabled, role);
        this.hiringDate = hiringDate;
    }
}
