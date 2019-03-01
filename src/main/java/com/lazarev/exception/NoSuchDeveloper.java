package com.lazarev.exception;

public class NoSuchDeveloper extends BuildServiceApplicationException {
    public NoSuchDeveloper(String no_such_developer) {
        super(no_such_developer);
    }
}
