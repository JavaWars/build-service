package com.lazarev.controller.rest;

import com.lazarev.model.Activity;
import com.lazarev.service.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class ActivityRestController {

    @Autowired
    private ActivitiesService activitiesService;

    @RequestMapping(value = "/activities",method = RequestMethod.GET)
    public ResponseEntity<Object> getAllActivities(Long developerId){

        if (developerId!=null){return new ResponseEntity<Object>(activitiesService.getActivitiesForDeveloperId(developerId),HttpStatus.OK);}
        return new ResponseEntity<Object>(activitiesService.getAllActivities(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/activities",method = RequestMethod.POST)
    public ResponseEntity<Object> insertActivities(Authentication authentication,@RequestBody Activity activity){
//        System.out.println(authentication.getName());
        //// TODO: 01.03.2019  check is admin for developer
        activitiesService.insert(activity);
        return new ResponseEntity<>("activity inserted",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/activities",method = RequestMethod.PUT)
    public ResponseEntity<Object> updateActivities(Authentication authentication,@RequestBody Activity activity,Long activityId){
        System.out.println(authentication.getName());
        //// TODO: 01.03.2019  check is admin for current developer
        activitiesService.insert(activity);
        return new ResponseEntity<>("activity updated",HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/activities",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteActivities(Authentication authentication, Long activityId){
        //// TODO: 01.03.2019  check is admin for current developer
        System.out.println(authentication.getName());
        activitiesService.remove(activityId);
        return new ResponseEntity<>("activity deleted",HttpStatus.OK);
    }

}
