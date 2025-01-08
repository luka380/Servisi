package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("VERIFICATION")
public class VerificationNotification extends BaseNotification {
    private String verificationCode;

    @Builder
    public VerificationNotification(UUID id, MessageType type, BaseUser receiverId, LocalDateTime createdAt, boolean isRead, String verificationCode) {
        super(id, type, receiverId, createdAt, isRead);
        this.verificationCode = verificationCode;
    }
}
