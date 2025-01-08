package com.project.prjx.Data.Model.Entity.Notifications;

import jakarta.persistence.Entity;

@Entity
public class VerificationNotification extends BaseNotification {
    private String verificationCode;
}
