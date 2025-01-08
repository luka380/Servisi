package com.project.prjx.API;

import com.project.prjx.Data.Mappers.Mappers;
import com.project.prjx.Data.Model.Dto.Notifications.NotificationData;
import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Data.Model.Entity.Notifications.BaseNotification;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Security.UserAuthentication;
import com.project.prjx.Services.NotificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class Notifications {
    private final NotificationService notificationService;

    public Notifications(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/")
    public ResponseEntity<List<NotificationData>> getMaybeFiltered(@RequestParam(required = false) String dateFrom,
                                                                   @RequestParam(required = false) String dateTo,
                                                                   @RequestParam(required = false) String type,
                                                                   @RequestParam(required = false) String email) {

        UserAuthentication user = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();

        List<BaseNotification> notifications = notificationService.getByFilter(MessageType.fromString(type), LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo), email, user.getUser());
        return ResponseEntity.ok(Mappers.NotificationMapper.reverseMap(notifications));
    }

    @GetMapping("/adminNotifications")
    public ResponseEntity<List<NotificationData>> getMaybeFilteredAdmin(@RequestParam String dateFrom,
                                                                        @RequestParam String dateTo,
                                                                        @RequestParam String type,
                                                                        @RequestParam String email) {

        UserAuthentication user = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto userDto = new BaseUserDto(null, null, null, null, null, null, null, null, null, null);

        List<BaseNotification> notifications = notificationService.getByFilter(MessageType.fromString(type), LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo), email, userDto);
        return ResponseEntity.ok(Mappers.NotificationMapper.reverseMap(notifications));
    }
}
