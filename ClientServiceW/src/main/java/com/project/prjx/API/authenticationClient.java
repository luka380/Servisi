package com.project.prjx.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.prjx.Data.Model.Dto.Notification.NotificationData;
import com.project.prjx.Data.Model.Dto.Users.ClientDto;
import com.project.prjx.Data.Model.Dto.Users.ClientRegistrationRequest;
import com.project.prjx.Data.Model.Dto.Users.EmailDto;
import com.project.prjx.Data.Model.Dto.Users.LoginRequest;
import com.project.prjx.Data.Model.MessageType;
import com.project.prjx.Exceptions.*;
import com.project.prjx.Security.JwtUtils;
import com.project.prjx.Services.ClientService;
import com.project.prjx.Services.MessagingService;
import com.project.prjx.Services.VerificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth/Client")
@RestController
public class authenticationClient {
    private final ClientService clientService;
    private final MessagingService messagingService;
    private final VerificationService verificationService;
    private final JwtUtils jwtUtils;

    public authenticationClient(ClientService clientService, MessagingService messagingService, VerificationService verificationService, JwtUtils jwtUtils) {
        this.clientService = clientService;
        this.messagingService = messagingService;
        this.verificationService = verificationService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/Login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        ClientDto client = clientService.getUserByUsername(loginRequest.getUsername());

        if (client == null) {
            throw new UserNotFoundException("No user found with username " + loginRequest.getUsername());
        } else if (!client.getPassword().equals(loginRequest.getPassword())) {
            throw new IncorrectCredentialsException();
        } else if (!client.getIsEnabled()) {
            throw new AccountDisabledException();
        } else if (!client.getEmail().isVerified()) {
            throw new AccountNotVerifiedException();
        }

        String token = jwtUtils.generateToken(client);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/Register")
    public ResponseEntity<String> register(@Valid @RequestBody ClientRegistrationRequest registrationRequest, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
        ClientDto existingClient = clientService.getUserByUsername(registrationRequest.getUsername());
        if (existingClient != null) {
            throw new UsernameAlreadyExistsException();
        }

        ClientDto c = ClientDto.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .isEnabled(true)
                .birthday(null)
                .username(registrationRequest.getUsername())
                .password(registrationRequest.getPassword())
                .email(new EmailDto(0, registrationRequest.getEmail(), false))
                .build();

        ClientDto newClient = clientService.saveUser(c);
        String verificationCode = verificationService.createVerification(newClient.getEmail());

        NotificationData data = NotificationData.builder()
                .receiverEmail(c.getEmail().getEmail())
                .receiverName(c.getFirstName() + " " + c.getLastName())
                .receiverId(newClient.getId().toString())
                .securityCode(verificationCode)
                .type(MessageType.ACTIVATION)
                .build();

        messagingService.sendAsyncMessage(data);

        return ResponseEntity.ok("Successfully registered");
    }

    @PostMapping("/SendPassReset")
    public ResponseEntity<String> sendResetReq(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String username = body.get("username");

        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        ClientDto clientDto = clientService.getUserByUsername(username);

        if (clientDto == null) {
            throw new UserNotFoundException("No user found ");
        } else if (!clientDto.getIsEnabled()) {
            throw new AccountDisabledException();
        }

        String code = verificationService.createPassResReq(clientDto);

        NotificationData data = NotificationData.builder()
                .receiverEmail(clientDto.getEmail().getEmail())
                .receiverName(clientDto.getFirstName() + " " + clientDto.getLastName())
                .receiverId(clientDto.getId().toString())
                .securityCode(code)
                .type(MessageType.PASSWORD_RESET)
                .build();

        messagingService.sendAsyncMessage(data);

        return ResponseEntity.ok("Verification code sent to your email");
    }

    @PostMapping("/PassReset/{code}")
    public ResponseEntity<String> resetReq(@PathVariable String code, @RequestBody Map<String, String> body, HttpServletResponse response) {

        String password = body.get("password");

        if (password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        if (verificationService.verifyPasswordChange(code, password)) {
            return ResponseEntity.ok("Password successfully changed");
        }

        return ResponseEntity.badRequest().body("Verification code is invalid");
    }

    @GetMapping("/Verification/{code}")
    public ResponseEntity<String> verify(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) {
        boolean state = verificationService.verifyVerificationCode(code);
        if (state) {
            return ResponseEntity.ok("Successfully verified");
        }
        return ResponseEntity.badRequest().body("Verification code is invalid");
    }

    @PostMapping("/SendVerification")
    public ResponseEntity<String> sendVerification(@Valid @RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        ClientDto client = clientService.getUserByUsername(loginRequest.getUsername());

        if (client == null) {
            throw new UserNotFoundException("No user found with username " + loginRequest.getUsername());
        } else if (!client.getPassword().equals(loginRequest.getPassword())) {
            throw new IncorrectCredentialsException();
        } else if (!client.getIsEnabled()) {
            throw new AccountDisabledException();
        }

        if (client.getEmail().isVerified()) {
            return ResponseEntity.badRequest().body("Email already verified");
        } else if (!client.getEmail().getEmail().equals(loginRequest.getEmail())) {
            return ResponseEntity.badRequest().body("");
        }

        String code = verificationService.createVerification(client.getEmail());
        NotificationData data = NotificationData.builder()
                .receiverEmail(client.getEmail().getEmail())
                .receiverName(client.getFirstName() + " " + client.getLastName())
                .receiverId(client.getId().toString())
                .securityCode(code)
                .type(MessageType.ACTIVATION)
                .build();

        messagingService.sendAsyncMessage(data);
        return ResponseEntity.ok("Verification code sent to your email");

    }
}