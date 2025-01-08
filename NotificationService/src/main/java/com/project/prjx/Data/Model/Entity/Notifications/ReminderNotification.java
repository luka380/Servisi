package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@DiscriminatorValue("REMINDER")
public class ReminderNotification extends BaseNotification {
    @Builder
    public ReminderNotification(UUID id, MessageType type, BaseUser receiverId, LocalDateTime createdAt, boolean isRead) {
        super(id, type, receiverId, createdAt, isRead);
    }
}
