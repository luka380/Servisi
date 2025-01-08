package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Users.VerificationEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationEntry, Integer> {
    VerificationEntry findFirstByVerificationCodeAndExpiryDateAfter(String verificationCode, java.time.LocalDateTime expiryDate);
}
