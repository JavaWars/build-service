package com.lazarev.controller.exceptionResolver;

import com.lazarev.exception.NoSuchUser;
import com.lazarev.exception.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserResolver {

    @ExceptionHandler(value = NoSuchUser.class)
    public ResponseEntity<String> userError(NoSuchUser exc){
        System.out.println("no user exception");
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = UserAlreadyExist.class)
    public ResponseEntity<String> userError(UserAlreadyExist exc){
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.OK);
    }


}
