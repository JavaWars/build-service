package com.lazarev.controller.rest;

import com.lazarev.model.Role;
import com.lazarev.model.User;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    //one of the option
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity<Object> user(Long id,String email,String phone){

        if (id!=null){return new ResponseEntity<Object>(userService.getById(id), HttpStatus.OK);}

        if (email!=null){return new ResponseEntity<Object>(userService.getByEmail(email), HttpStatus.OK);}

        if (phone!=null){return new ResponseEntity<Object>(userService.getByEmail(email), HttpStatus.OK);}

        return new ResponseEntity<Object>(userService.getAllUsers(),HttpStatus.OK);
    }

    @RequestMapping(value = "/user-searcher",method = RequestMethod.GET)
    public ResponseEntity<List<User>> findUserByProperties(String email, String phone, String name){
        return new ResponseEntity<List<User>>(userService.findByParams(email,phone,name),HttpStatus.OK);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody User user){
        userService.insertNewUser(user);
        return new ResponseEntity<>("user inserted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(Long userId){
        //// TODO: 06.03.2019  is it me
        userService.delete(userId);
        return new ResponseEntity<>("User deleted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DELETED')")
    @RequestMapping(value = "/user/refresh",method = RequestMethod.PUT)
    public ResponseEntity<Object> userRefresh(Long userId){
        userService.refresh(userId);
        return new ResponseEntity<>("user status refreshed",HttpStatus.OK);
    }
}
