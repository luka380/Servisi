package com.project.prjx.Data.Model.Entity.Notifications;

import com.project.prjx.Data.Model.MessageType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private MessageType type;
    private String title;
    private String body;
}
