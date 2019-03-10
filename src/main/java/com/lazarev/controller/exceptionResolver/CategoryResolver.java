package com.lazarev.controller.exceptionResolver;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.exception.NoSuchCategory;
import com.lazarev.exception.NoSuchDeveloper;
import com.lazarev.exception.NoSuchUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryResolver {

    @ExceptionHandler(value = BuildServiceApplicationException.class)
    public String applicationError(BuildServiceApplicationException exc, Model model){
        System.out.println("global application exception");
        model.addAttribute("errorMsg",exc.getMessage());
        model.addAttribute("stackTrace",exc.getStackTrace().toString());
        return "error";
    }

}
