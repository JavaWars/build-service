package com.lazarev.controller.rest;

import com.lazarev.model.Developer;
import com.lazarev.model.User;
import com.lazarev.service.DeveloperService;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class DeveloperRestController {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @RequestMapping(value = "/developers",method = RequestMethod.POST)
    public ResponseEntity<Object> developerNewSave(@RequestBody Developer developer){
        System.out.println("create/update developer");
        User founder = UserService.getCurrentUser();

        developerService.insert(developer,founder.getId());
        SecurityContextHolder.clearContext();

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


    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/developers_admin",method = RequestMethod.GET)
    public ResponseEntity<Object> getDeveloperAdmins(){
        return new ResponseEntity<>(developerService.getAdminsForDeveloper(UserService.getCurrentUser().getId()),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/developer",method = RequestMethod.GET)
    public ResponseEntity<Object> getDeveloperByMyUserId(){
        return new ResponseEntity<Object>(developerService.getDeveloperByCurrentUserId(UserService.getCurrentUser().getId()).getId(),HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/developers_admin/{adminId}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDeveloperAdmin(@PathVariable("adminId") Long adminId){

        developerService.deleteAdmin(adminId, UserService.getCurrentUser().getId());
        return new ResponseEntity<>("developer deleted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/developers_admin/{newAdminId}",method = RequestMethod.POST)
    public ResponseEntity<Object> developerWithId(@PathVariable("newAdminId") Long newAdminId){
        developerService.setNewAdminForDeveloper(newAdminId,
                UserService.getCurrentUser()//this is developer owner
        );
        return new ResponseEntity<>("new admin added for u company",HttpStatus.OK);
    }
}
