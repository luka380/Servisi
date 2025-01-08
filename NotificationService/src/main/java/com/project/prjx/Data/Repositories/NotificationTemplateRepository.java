package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Notifications.NotificationTemplate;
import com.project.prjx.Data.Model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Integer> {
    NotificationTemplate findNotificationTemplateByType(MessageType type);
}
