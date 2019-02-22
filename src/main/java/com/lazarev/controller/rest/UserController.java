package com.lazarev.controller.rest;

import com.lazarev.repository.UserRepository;
import com.lazarev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository repository;

    @RequestMapping("user")
    public String test() {
        repository.save(new User());
        System.out.println( String.valueOf(repository.findAll().toArray().toString()));
        return "index";
    }

}
