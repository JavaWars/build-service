package com.lazarev.exception;

public class NoSuchUser extends BuildServiceApplicationException {
    public NoSuchUser(String no_such_category) {
        super(no_such_category);
    }
}
