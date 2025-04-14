package com.learnSphere.auth_service.globalException;

public class UserNotAuthenticatedException extends Exception{
    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
