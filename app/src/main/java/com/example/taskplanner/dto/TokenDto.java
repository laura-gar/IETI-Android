package com.example.taskplanner.dto;

import java.util.Date;

public class TokenDto {
    private String token;
    private String userId;
    private Date expirationDate;

    public TokenDto(String token, Date expirationDate, String userId) {
        this.token = token;
        this.userId = userId;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getUserId() {
        return userId;
    }
}
