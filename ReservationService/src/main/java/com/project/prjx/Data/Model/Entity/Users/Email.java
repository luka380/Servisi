package com.project.prjx.Data.Model.Entity.Users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "baseuser_id"})})
public class Email {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @jakarta.validation.constraints.Email
    @NotBlank
    private String email;
    private Boolean isVerified = false;
    @OneToOne()
    @JoinColumn(name = "baseuser_id", nullable = false)
    private BaseUser baseUser;

    public Email(String email, Boolean isVerified) {
        this.email = email;
        this.isVerified = isVerified;
    }

    public Email(String email, Boolean isVerified, BaseUser baseUser) {
        this.email = email;
        this.isVerified = isVerified;
        this.baseUser = baseUser;
    }
}
