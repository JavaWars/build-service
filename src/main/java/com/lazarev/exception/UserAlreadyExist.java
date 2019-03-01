package com.lazarev.exception;

public class UserAlreadyExist extends BuildServiceApplicationException {
    public UserAlreadyExist(String mess) {
        super(mess);
    }
}
