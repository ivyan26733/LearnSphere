package com.learnSphere.auth_service.globalException;

public class TokenGenerationException extends Exception{
    public TokenGenerationException(String message) {
        super(message);
    }
}
