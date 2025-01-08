package com.project.prjx.API;

import com.project.prjx.Data.Model.Dto.Users.BaseUserDto;
import com.project.prjx.Security.UserAuthentication;
import com.project.prjx.Services.ServiceInterface.BaseClientServiceInterface;
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

    private final BaseClientServiceInterface<BaseUserDto> clientService;

    public accountManagement(BaseClientServiceInterface<BaseUserDto> clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfileData() {
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("firstName", user.getFirstName());
        map.put("lastName", user.getLastName());
        map.put("email", user.getEmail().email());
        map.put("number", user.getPhoneNumber().toString());
        map.put("birthday", user.getBirthday().toString());

        return ResponseEntity.ok(map);
    }

    @GetMapping("/preferences")
    public ResponseEntity<String> getPreferencesData() {
        return ResponseEntity.ok("");
    }

    @GetMapping("/settings")
    public ResponseEntity<String> getSettingsData() {
        return ResponseEntity.ok("");
    }

    @Transactional
    @PostMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody Map<String, String> profile) {
        UserAuthentication userAuthentication = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        BaseUserDto user = userAuthentication.getUser();
        BaseUserDto updated = clientService.getUserById(user.getId());

        if(profile.containsKey("username") && !(user.getUsername().equals(profile.get("username"))) && clientService.getUserByUsername(profile.get("username")) == null){
            updated.setUsername(profile.get("username"));
        }
        if(profile.containsKey("firstName") && !(user.getFirstName().equals(profile.get("firstName")) && !profile.get("firstName").isBlank())){
            updated.setFirstName(profile.get("firstName"));
        }
        if(profile.containsKey("lastName") && !(user.getLastName().equals(profile.get("lastName")) && !profile.get("lastName").isBlank())){
            updated.setLastName(profile.get("lastName"));
        }
        if(profile.containsKey("email")){
            //
        }
        if(profile.containsKey("number") && !(user.getPhoneNumber().toString().equals(profile.get("number")) && !profile.get("number").isBlank())){
            updated.setPhoneNumber(Long.parseLong(profile.get("number")));
        }
        if(profile.containsKey("birthday") && !(user.getBirthday().toString().equals(profile.get("birthday")) && !profile.get("birthday").isBlank())){
            try {
                LocalDateTime date = LocalDateTime.parse(profile.get("birthday"));
                updated.setBirthday(date);
            }
            catch (Exception e){
                //
            }
        }

        clientService.saveUser(updated);

        return ResponseEntity.ok("");
    }

    @PostMapping("/preferences")
    public ResponseEntity<String> updatePreferences(@RequestBody Map<String, String> preferences) {
        return ResponseEntity.ok("");
    }

    @PostMapping("/settings")
    public ResponseEntity<String> updateSettings(@RequestBody Map<String, String> settings) {
        return ResponseEntity.ok("string");
    }

}
