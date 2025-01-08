package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Notifications.BaseNotification;
import com.project.prjx.Data.Model.Entity.Users.BaseUser;
import com.project.prjx.Data.Model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BaseNotificationRepository extends JpaRepository<BaseNotification, UUID> {

    List<BaseNotification> findByType(MessageType type);

    List<BaseNotification> findByTypeAndReceiverId(MessageType type, BaseUser receiverId);

    @Query("SELECT e FROM BaseNotification e " +
            "WHERE (:type IS NULL OR e.type = :type) " +
            "AND ((:startDate IS NULL AND :endDate IS NULL) OR (e.createdAt BETWEEN :startDate AND :endDate))" +
            "AND (:email IS NULL OR e.receiverId.email.email = :email)" +
            "AND (:receiverId IS NULL OR e.receiverId.id = :receiverId)")
    List<BaseNotification> findByCriteria(@Param("type") String type,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          @Param("email") String email,
                                          @Param("receiverId") UUID receiverId);
}
