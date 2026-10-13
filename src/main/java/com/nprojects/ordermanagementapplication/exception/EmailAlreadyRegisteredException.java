package com.nprojects.ordermanagementapplication.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{

    public EmailAlreadyRegisteredException(String message){
        super(message);
    }
}
