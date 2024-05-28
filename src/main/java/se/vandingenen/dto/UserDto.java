package se.vandingenen.dto;

import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull(message = "is required")
    private String username;

    @NotNull(message = "is required")
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}
