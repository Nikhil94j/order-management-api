package com.nprojects.ordermanagementapplication.model;

public class RegisterResponse {

    private Long id;
    private String email;

    public RegisterResponse(Long id,String email){
        this.id=id;
        this.email=email;
    }

    public Long getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }


}
