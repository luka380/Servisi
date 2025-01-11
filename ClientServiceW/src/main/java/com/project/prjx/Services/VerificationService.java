package com.project.prjx.Services;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.Entity.Users.VerificationEntry;
import com.project.prjx.Data.Repositories.BaseUserRepository;
import com.project.prjx.Data.Repositories.EmailsRepository;
import com.project.prjx.Data.Repositories.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VerificationService {
    private final VerificationRepository verificationRepository;
    private final EmailsRepository emailsRepository;
    private final BaseUserRepository baseUserRepository;

    public VerificationService(VerificationRepository verificationRepository, EmailsRepository emailsRepository, BaseUserRepository baseUserRepository) {
        this.verificationRepository = verificationRepository;
        this.emailsRepository = emailsRepository;
        this.baseUserRepository = baseUserRepository;
    }

    public String createVerification(EmailDto emailDto) {
        String verificationCode = UUID.randomUUID().toString();
        VerificationEntry verificationEntry = VerificationEntry.builder()
                .email(Mappers.EmailMapper.map(emailDto))
                .verificationCode(verificationCode)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();

        verificationRepository.save(verificationEntry);
        return verificationCode;
    }

    public String createPassResReq(BaseUserDto baseUserDto) {
        String verificationCode = UUID.randomUUID().toString();
        VerificationEntry verificationEntry = VerificationEntry.builder()
                .baseUser(Mappers.BaseUserMapper.reverseMap(baseUserDto))
                .verificationCode(verificationCode)
                .expiryDate(LocalDateTime.now().plusMinutes(5))
                .build();

        verificationRepository.save(verificationEntry);
        return verificationCode;
    }

    @Transactional
    public boolean verifyVerificationCode(String verificationCode) {
        VerificationEntry ve = verificationRepository.findFirstByVerificationCodeAndExpiryDateAfter(verificationCode, LocalDateTime.now());
        if (ve == null)
            return false;

        ve.getEmail().setIsVerified(true);
        emailsRepository.save(ve.getEmail());
        verificationRepository.delete(ve);
        return true;
    }

    @Transactional
    public boolean verifyPasswordChange(String verificationCode, String newPassword) {
        VerificationEntry ve = verificationRepository.findFirstByVerificationCodeAndExpiryDateAfter(verificationCode, LocalDateTime.now());
        if (ve == null)
            return false;

        BaseUser baseUser = ve.getBaseUser();
        baseUser.setPassword(newPassword);
        baseUserRepository.save(baseUser);
        verificationRepository.delete(ve);
        return true;
    }
}
