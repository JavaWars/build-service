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

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public ResponseEntity<Object> user(Long id,String email,String phone){

        if (id!=null){return new ResponseEntity<Object>(userService.getById(id), HttpStatus.OK);}

        if (email!=null){return new ResponseEntity<Object>(userService.getByEmail(email), HttpStatus.OK);}

        if (phone!=null){return new ResponseEntity<Object>(userService.getByEmail(email), HttpStatus.OK);}

        return new ResponseEntity<Object>(userService.getAllUsers(),HttpStatus.OK);
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public ResponseEntity<Object> insert(@RequestBody User user){
        userService.insertNewUser(user);
        return new ResponseEntity<>("user inserted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public ResponseEntity<Object> setUserRole(Role role,Long userId){
        userService.insertRole(userId,role);
        return new ResponseEntity<>("todo setting Role for user",HttpStatus.OK);
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(Long userId){
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
