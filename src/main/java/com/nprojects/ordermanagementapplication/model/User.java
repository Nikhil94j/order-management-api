package com.nprojects.ordermanagementapplication.model;

import jakarta.persistence.*;

@Entity
@Table(name="app_user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    private String password;

protected User(){

}

public User(String email,String password){
    this.email=email;
    this.password=password;
}

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}
