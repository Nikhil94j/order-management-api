package com.nprojects.ordermanagementapplication.model;

public class LoginResponse {

    private Long id;
    private String email;
    private String accessToken;
    private String tokenType;

    public LoginResponse(Long id,String email,String accessToken){
        this.id=id;
        this.email=email;
        this.accessToken=accessToken;
        this.tokenType="Bearer";
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
