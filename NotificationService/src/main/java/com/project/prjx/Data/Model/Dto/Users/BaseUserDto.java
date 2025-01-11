package com.project.prjx.Data.Model.Dto.Users;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserDto {
    private UUID id;
    private String username;
    private String password;
    private EmailDto email;
    private LocalDateTime birthday;
    private String firstName;
    private String lastName;
    private Number phoneNumber;
    private Boolean isEnabled;
    private String role;
}