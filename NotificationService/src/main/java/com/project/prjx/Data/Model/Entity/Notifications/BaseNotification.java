package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "table_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private MessageType type;
    @ManyToOne()
    @JoinColumn(name = "baseuser_id", nullable = true)
    private BaseUser receiverId;
    private LocalDateTime createdAt;
    private boolean isRead;

    public BaseNotification(UUID id, MessageType type, BaseUser receiverId, LocalDateTime createdAt, boolean isRead) {
        this.id = id;
        this.type = type;
        this.receiverId = receiverId;
        this.createdAt = createdAt;
        this.isRead = isRead;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
