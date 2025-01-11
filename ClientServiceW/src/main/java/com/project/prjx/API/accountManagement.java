package com.project.prjx.API;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Security.UserAuthentication;
import com.project.prjx.Services.BaseUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/account")
@RestController
public class accountManagement {

    private final BaseUserService userService;

    public accountManagement(BaseUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, String>> getProfileData() {
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail().getEmail());
        map.put("email_verified", user.getEmail().isVerified() ? "true" : "false");
        map.put("number", "06000");
        map.put("birthday", user.getBirthday().toString());
        map.put("enabled", user.getIsEnabled() ? "true" : "false");
        map.put("role", user.getRole());
        map.put("id", user.getId().toString());

        return ResponseEntity.ok(map);
    }

    @Transactional
    @PatchMapping("/")
    public ResponseEntity<String> updateProfile(@RequestBody Map<String, String> profile) {
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();
        BaseUserDto updated = userService.getUserById(user.getId());

        if (profile.containsKey("username") && !(user.getUsername().equals(profile.get("username"))) && userService.getUserByUsername(profile.get("username")) == null) {
            updated.setUsername(profile.get("username"));
        }
        if (profile.containsKey("firstName") && !(user.getFirstName().equals(profile.get("firstName")) && !profile.get("firstName").isBlank())) {
            updated.setFirstName(profile.get("firstName"));
        }
        if (profile.containsKey("lastName") && !(user.getLastName().equals(profile.get("lastName")) && !profile.get("lastName").isBlank())) {
            updated.setLastName(profile.get("lastName"));
        }

        userService.saveUser(updated);

        return ResponseEntity.ok("");
    }
}
