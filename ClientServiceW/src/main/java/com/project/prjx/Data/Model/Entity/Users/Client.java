package com.project.prjx.Data.Model.Entity.Users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@DiscriminatorValue("CLIENT")
public class Client extends BaseUser {
    private int reservationsCount;

    public Client(String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Boolean isEnabled) {
        super(username, password, email, birthday, firstName, lastName, isEnabled);
    }

    @Builder
    public Client(UUID id, String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Boolean isEnabled, String role) {
        super(id, username, password, email, birthday, firstName, lastName, isEnabled, role);
    }

    public Client() {

    }
}
