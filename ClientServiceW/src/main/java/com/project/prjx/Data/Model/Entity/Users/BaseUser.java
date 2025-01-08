package com.project.prjx.Data.Model.Entity.Users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseUser {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToOne(mappedBy = "baseUser")
    private Email email;
    @Column(nullable = true)
    private LocalDateTime birthday;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = true)
    private Number phoneNumber;
    @Column(nullable = false)
    private Boolean isEnabled;
    @Column(nullable = false)
    private String role;

    public BaseUser(String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Number phoneNumber, Boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isEnabled = isEnabled;
        this.role = "ROLE_USER";
    }

    public BaseUser(UUID id, String username, String password, Email email, LocalDateTime birthday, String firstName, String lastName, Number phoneNumber, Boolean isEnabled, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isEnabled = isEnabled;
        this.role = role;
    }

    public BaseUser() {
    }
}
