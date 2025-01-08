package com.project.prjx.Data.Model.Entity.Users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(indexes = @Index(columnList = "verification_code"))
public class VerificationEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "email_id", nullable = true)
    private Email email;
    @ManyToOne
    @JoinColumn(name = "baseuser_id", nullable = true)
    private BaseUser baseUser;
    @Column(nullable = false)
    private String verificationCode;
    @Column(nullable = false)
    private LocalDateTime expiryDate;
}
