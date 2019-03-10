package com.lazarev.exception;

public class NoSuchLogoType extends BuildServiceApplicationException {
    public NoSuchLogoType(String no_such_logo_type) {
        super(no_such_logo_type);
    }
}
