package com.project.prjx.Data.Repositories;

import com.project.prjx.Data.Model.Entity.Notifications.BaseNotification;
import com.project.prjx.Data.Model.MessageType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BaseNotificationRepository extends JpaRepository<BaseNotification, UUID> {

    @Query("SELECT e FROM BaseNotification e " +
            "WHERE (:type IS NULL OR e.type = :type) " +
            "AND (e.createdAt BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp))" +
            "AND (:email IS NULL OR e.receiverEmail = :email)" +
            "AND (:receiverId IS NULL OR e.receiverId = :receiverId)")
    List<BaseNotification> findByCriteria(@Param("type") String type,
                                          @Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          @Param("email") String email,
                                          @Param("receiverId") UUID receiverId);
}
