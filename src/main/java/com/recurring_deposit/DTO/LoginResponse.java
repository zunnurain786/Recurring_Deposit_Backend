package com.recurring_deposit.DTO;

import java.time.LocalDateTime;

public class LoginResponse {

    private String message;

    private Long userId;

    private String fullName;

    private String email;

    private String mobileNumber;

    private String role;

    private Boolean active;

    private Boolean profileCompleted;

    private LocalDateTime createdAt;

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(
            String message,
            Long userId,
            String fullName,
            String email,
            String mobileNumber,
            String role,
            Boolean active,
            Boolean profileCompleted,
            LocalDateTime createdAt,
            String token) {

        this.message = message;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.role = role;
        this.active = active;
        this.profileCompleted = profileCompleted;
        this.createdAt = createdAt;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(Boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}