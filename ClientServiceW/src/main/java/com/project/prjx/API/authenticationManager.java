package com.project.prjx.API;

import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Dto.Users.LoginRequest;
import com.project.prjx.Data.Model.Dto.Users.ManagerDto;
import com.project.prjx.Data.Model.Dto.Users.ManagerRegistrationRequest;
import com.project.prjx.Exceptions.*;
import com.project.prjx.Security.JwtUtils;
import com.project.prjx.Services.ServiceImpl.MessagingServiceImpl;
import com.project.prjx.Services.ServiceImpl.VerificationServiceImpl;
import com.project.prjx.Services.ServiceInterface.ManagerServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/auth/Manager")
@RestController
public class authenticationManager {
    private final ManagerServiceInterface managerService;
    private final MessagingServiceImpl messagingService;
    private final VerificationServiceImpl verificationServiceImpl;
    private final JwtUtils jwtUtils;

    public authenticationManager(ManagerServiceInterface managerService, MessagingServiceImpl messagingService, VerificationServiceImpl verificationServiceImpl, JwtUtils jwtUtils) {
        this.managerService = managerService;
        this.messagingService = messagingService;
        this.verificationServiceImpl = verificationServiceImpl;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/Login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        ManagerDto manager = managerService.getUserByUsername(loginRequest.getUsername());

        if (manager == null) {
            throw new UserNotFoundException("No user found with username " + loginRequest.getUsername());
        } else if (!manager.getPassword().equals(loginRequest.getPassword())) {
            throw new IncorrectCredentialsException();
        } else if (!manager.getIsEnabled()) {
            throw new AccountDisabledException();
        } else if (!manager.getEmail().isVerified()) {
            throw new AccountNotVerifiedException();
        }

        String token = jwtUtils.generateToken(manager);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/Register")
    public ResponseEntity<String> register(@Valid @RequestBody ManagerRegistrationRequest registrationRequest, HttpServletRequest request, HttpServletResponse response) {
        ManagerDto existingManager = managerService.getUserByUsername(registrationRequest.getUsername());

        if (existingManager != null) {
            throw new UsernameAlreadyExistsException();
        }

        ManagerDto c = ManagerDto.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .isEnabled(true)
                .birthday(null)
                .phoneNumber(null)
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .email(new EmailDto(null, registrationRequest.getEmail(), false))
                .hiringDate(registrationRequest.getHireDate())
                .restaurant(null)
                .build();

        ManagerDto newManager = managerService.saveUser(c);
        String verificationCode = verificationServiceImpl.createVerification(newManager.getEmail());

        messagingService.sendAsyncMessage(verificationCode);
        messagingService.sendAsyncMessage("");//greeting for registration

        return ResponseEntity.ok("Successfully registered");
    }

    @PostMapping("/Finduser")
    public ResponseEntity<String> findUser(@RequestBody Map<String, String> body, HttpServletRequest request, HttpServletResponse response) {
        String email = body.get("email");
        if (!isValidEmail(email)) {
            throw new RuntimeException("Invalid email");
        }

        List<ManagerDto> clients = managerService.getManagersByEmail(email);

        StringBuilder builder = new StringBuilder();

        clients.forEach(c->{
            builder.append(c.getUsername()).append("\n");
        });

        messagingService.sendAsyncMessage(builder.toString());

        return ResponseEntity.ok("Account data will be sent to email");
    }

    @PostMapping("/SendPassReset")
    public ResponseEntity<String> sendResetReq(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String username = body.get("username");

        if(username == null || username.isBlank()){
            return ResponseEntity.badRequest().body("Username is required");
        }

        ManagerDto manager = managerService.getUserByUsername(username);

        if (manager == null) {
            throw new UserNotFoundException("No user found ");
        } else if (!manager.getIsEnabled()) {
            throw new AccountDisabledException();
        }

        String code = verificationServiceImpl.createPassResReq(manager);
        messagingService.sendAsyncMessage(code);
        return ResponseEntity.ok("Verification code sent to your email");
    }

    @PostMapping("/PassReset/{code}")
    public ResponseEntity<String> resetReq(@PathVariable String code, @RequestBody Map<String, String> body, HttpServletResponse response) {

        String password = body.get("password");

        if(password == null || password.isBlank()){
            return ResponseEntity.badRequest().body("Password is required");
        }

        if(verificationServiceImpl.verifyPasswordChange(code, password)){
            messagingService.sendAsyncMessage("Password successfully changed");
            return ResponseEntity.ok("Password successfully changed");
        }

        return ResponseEntity.badRequest().body("Verification code is invalid");
    }

    @GetMapping("/Verification/{code}")
    public ResponseEntity<String> verify(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) {
        boolean state = verificationServiceImpl.verifyVerificationCode(code);
        if(state){
            return ResponseEntity.ok("Successfully verified");
        }
        return ResponseEntity.badRequest().body("Verification code is invalid");
    }

    @PostMapping("/SendVerification")
    public ResponseEntity<String> sendVerification(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        ManagerDto manager = managerService.getUserByUsername(loginRequest.getUsername());

        if (manager == null) {
            throw new UserNotFoundException("No user found with username " + loginRequest.getUsername());
        } else if (!manager.getPassword().equals(loginRequest.getPassword())) {
            throw new IncorrectCredentialsException();
        } else if (!manager.getIsEnabled()) {
            throw new AccountDisabledException();
        }

        if (manager.getEmail().isVerified()) {
            return ResponseEntity.badRequest().body("Email already verified");
        } else if (!manager.getEmail().email().equals(loginRequest.getEmail())) {
            return ResponseEntity.badRequest().body("");
        }

        String code = verificationServiceImpl.createVerification(manager.getEmail());
        messagingService.sendAsyncMessage(code);
        return ResponseEntity.ok("Verification code sent to your email");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
}
