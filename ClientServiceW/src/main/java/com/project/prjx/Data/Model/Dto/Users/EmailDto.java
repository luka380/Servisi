package com.project.prjx.Data.Model.Dto.Users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDto {
    int id;
    String email;
    boolean isVerified;
}
