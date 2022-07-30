package me.hung.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDto {

    @NotNull(message = "Username must be not null")
    @Email(message = "Bad email")
    private String email;

    @NotNull(message = "Email must be not null")
    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{3,20}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "Bad username")
    private String username;
    @NotNull(message = "Password must be not null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Bad password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotNull(message = "Password confirm must be not null")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Bad password confirm")

    @JsonProperty(value = "password_confirm", access = JsonProperty.Access.WRITE_ONLY)
    private String passwordConfirm;

    public UserDto(String email, String username, String password, String passwordConfirm) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
