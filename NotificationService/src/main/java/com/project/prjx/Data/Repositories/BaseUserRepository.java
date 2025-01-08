package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseUserRepository extends JpaRepository<BaseUser, UUID> {

}
