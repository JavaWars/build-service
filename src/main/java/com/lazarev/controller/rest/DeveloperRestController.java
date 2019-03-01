package com.lazarev.controller.rest;

import com.lazarev.model.Developer;
import com.lazarev.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeveloperRestController {

    @Autowired
    private DeveloperService developerService;

    @RequestMapping(value = "/developers/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> developerDelete(@PathVariable("id") String id){
        developerService.delete(Long.parseLong(id));
        return new ResponseEntity<>("developer deleted",HttpStatus.OK);
    }

    @RequestMapping(value = "/developers/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Object> developerWithId(@PathVariable("id") String id,@RequestBody Developer developer){
        System.out.println("dev_put");
        developerService.update(Long.parseLong(id),developer);
        return new ResponseEntity<>("developer updated",HttpStatus.OK);
    }

    @RequestMapping(value = "/developers",method = RequestMethod.POST)
    public ResponseEntity<Object> developerNewSave(@RequestBody Developer developer){
        developerService.insert(developer);
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
