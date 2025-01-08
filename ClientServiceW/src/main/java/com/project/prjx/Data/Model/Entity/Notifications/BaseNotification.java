package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BaseNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private MessageType type;
    @ManyToOne()
    @JoinColumn(name = "baseuser_id", nullable = false)
    private BaseUser receiverId;
    private LocalDateTime createdAt;
    private boolean isRead;
}
