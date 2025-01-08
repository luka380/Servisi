package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Users.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailsRepository extends JpaRepository<Email, Integer> {
}
