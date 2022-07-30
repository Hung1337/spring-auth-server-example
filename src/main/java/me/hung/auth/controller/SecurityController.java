package me.hung.auth.controller;

import me.hung.auth.dto.AuthRequest;
import me.hung.auth.dto.CustomResponse;
import me.hung.auth.dto.UserDto;
import me.hung.auth.entity.RoleEntity;
import me.hung.auth.entity.UserEntity;
import me.hung.auth.service.AuthenticateService;
import me.hung.auth.service.UserService;
import me.hung.auth.util.MessageReader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/security")
public class SecurityController {

    private final UserService userService;
    private final MessageReader messageReader;
    private final AuthenticateService authenticateService;

    private final PasswordEncoder passwordEncoder;

    public SecurityController(UserService userService, MessageReader messageReader, AuthenticateService authenticateService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.messageReader = messageReader;
        this.authenticateService = authenticateService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<CustomResponse> generateToken(@RequestBody AuthRequest req) {
        return authenticateService.authenticateUser(req);
    }

    @PostMapping("/register")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody UserDto userDto, Errors errors) {

        if (errors.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            errors
                    .getAllErrors()
                    .forEach(e -> errorMessages.add(e.getDefaultMessage()));
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse(400, userDto, errorMessages));
        }

        String username = userDto.getUsername();
        String email = userDto.getEmail();
        String password = userDto.getPassword();
        String passwordConfirm = userDto.getPasswordConfirm();

        UserEntity userByUsername = userService.getUserByUsername(username);

        boolean userNotNull = userByUsername != null;
        boolean userByEmailNotNull = userService.getUserByEmail(email) != null;
        boolean passwordMismatch = !password.equals(passwordConfirm);
        boolean doException = false;
        String message = "";

        if (passwordMismatch) {
            message = messageReader.getMessage("password.mismatch");
            doException = true;
        }

        if (userNotNull) {
            message = messageReader.getMessage("username.duplicate");
            doException = true;
        }

        if (userByEmailNotNull) {
            message = messageReader.getMessage("email.duplicate");
            doException = true;
        }

        if (doException) {
            return ResponseEntity
                    .status(400)
                    .body(
                            new CustomResponse(400, userDto, message)
                    );
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setUsername(username);
        userEntity.setRole(RoleEntity.ROLE_USER);

        userService.save(userEntity);
        return ResponseEntity
                .ok(new CustomResponse(200, userEntity, ""));

    }

}