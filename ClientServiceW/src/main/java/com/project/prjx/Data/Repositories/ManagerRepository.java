package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface ManagerRepository extends JpaRepository<Manager, UUID> {
}
