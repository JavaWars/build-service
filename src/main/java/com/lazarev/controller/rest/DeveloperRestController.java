package com.lazarev.controller.rest;

import com.lazarev.model.Developer;
import com.lazarev.model.User;
import com.lazarev.model.security_user.CustomUserDetails;
import com.lazarev.service.DeveloperService;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class DeveloperRestController {

    @Autowired
    private DeveloperService developerService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/developers/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> developerDelete(@PathVariable("id") String id){
        //// TODO: 06.03.2019  check is user owner for this developer
        developerService.delete(Long.parseLong(id));
        return new ResponseEntity<>("developer deleted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/developers/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> developerWithId(@PathVariable("id") String id,@RequestBody Developer developer){
        developerService.update(Long.parseLong(id),developer);
        return new ResponseEntity<>("developer updated",HttpStatus.OK);
    }

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(value = "/developers",method = RequestMethod.POST)
    public ResponseEntity<Object> developerNewSave( @RequestBody Developer developer){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User founder = authentication == null ? null : (User) authentication.getPrincipal();

        System.out.println("from db"+founder);

        developerService.insert(developer,founder);
        return new ResponseEntity<>("developer inserted",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/developers/{id}",method = RequestMethod.GET)
    public ResponseEntity<Object> developerById(@PathVariable("id") String id){
        return new ResponseEntity<Object>(developerService.developer(Long.parseLong(id)),HttpStatus.OK);
    }

    @RequestMapping(value = "/developers",method = RequestMethod.GET)
    public ResponseEntity<Object> developer(){
        return new ResponseEntity<Object>(developerService.allDeveloper(),HttpStatus.OK);
    }
}
