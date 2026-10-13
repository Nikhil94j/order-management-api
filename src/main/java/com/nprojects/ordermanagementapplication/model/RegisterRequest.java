package com.nprojects.ordermanagementapplication.model;

public class RegisterRequest {

    private String email;
    private String password;

    public RegisterRequest(){

    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password){
        this.password=password;
    }
}
