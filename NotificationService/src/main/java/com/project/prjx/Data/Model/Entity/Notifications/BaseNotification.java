package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "table_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BaseNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime createdAt;
    private boolean isRead;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String restaurantName;
    private String restaurantAddress;
    private String reservationTime;
    private String reservationDate;
    private String reservationStatus;
    private String receiverEmail;
    private String receiverName;
    private String receiverPhone;
    private UUID receiverId;
    private String senderName;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
