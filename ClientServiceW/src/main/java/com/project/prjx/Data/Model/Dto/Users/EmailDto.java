package com.project.prjx.Data.Model.Dto.Users;

import lombok.Builder;

@Builder
public record EmailDto (
        Integer id,
        String email,
        Boolean isVerified) {}
