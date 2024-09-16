package com.springProject.FirstProject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDto {

    private String userId;
    @NotEmpty
    @Size(min = 3, message = "Name should be at least 3 characters !!")
    private String name;
    @Email(message = "Invalid Email !!")
    private String email;
    @NotEmpty
    @Size(min = 5, max = 10, message = "Password must be min 5 characters and max 10 characters")
    private String password;
    @NotEmpty
    private String about;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
