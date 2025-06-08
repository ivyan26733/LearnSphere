package com.LearnSphere.enrollment_service.dto;


public class UserDeleteEvent {
    private String email;

    public UserDeleteEvent(String email) {
        this.email = email;
    }
    public UserDeleteEvent() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
