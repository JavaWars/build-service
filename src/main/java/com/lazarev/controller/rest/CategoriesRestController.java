package com.lazarev.controller.rest;

import com.lazarev.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoriesRestController {

    @Autowired
    private CategoriesService categoriesService;

    @RequestMapping(value = "/categories",method = RequestMethod.GET)
    public ResponseEntity<Object> categories(Long id,String name){

        if (id!=null){return new ResponseEntity<Object>(categoriesService.getCategory(id),HttpStatus.OK);}
        if (name!=null){return new ResponseEntity<Object>(categoriesService.getCategory(name),HttpStatus.OK);}

        return new ResponseEntity<Object>(categoriesService.getAllCategories(), HttpStatus.OK);
    }

}
