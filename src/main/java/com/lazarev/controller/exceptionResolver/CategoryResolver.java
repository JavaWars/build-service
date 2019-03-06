package com.lazarev.controller.exceptionResolver;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.exception.NoSuchCategory;
import com.lazarev.exception.NoSuchDeveloper;
import com.lazarev.exception.NoSuchUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryResolver {

    @ExceptionHandler(value = BuildServiceApplicationException.class)
    public ResponseEntity<String> applicationError(BuildServiceApplicationException exc){
        System.out.println("global application exception");
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NoSuchCategory.class)
    public ResponseEntity<String> categoryError(NoSuchCategory exc){
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = NoSuchDeveloper.class)
    public ResponseEntity<String> developerError(NoSuchDeveloper exc){
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.OK);
    }

}
