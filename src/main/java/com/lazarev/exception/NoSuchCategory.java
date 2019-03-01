package com.lazarev.exception;

public class NoSuchCategory extends BuildServiceApplicationException {
    public NoSuchCategory(String no_such_category) {
        super(no_such_category);
    }
}
