package com.project.prjx.Data.Model.Dto.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerRegistrationRequest extends ClientRegistrationRequest {
    @NotNull
    private LocalDateTime hireDate;
}
