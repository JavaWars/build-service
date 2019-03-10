package com.lazarev.controller.rest;

import com.lazarev.model.Product;
import com.lazarev.service.PropertiesService;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class ProductPropertiesRestController {

    @Autowired
    private PropertiesService propertiesService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/properties/{productId}",method = RequestMethod.POST)
    public ResponseEntity<Object> insertProductForDeveloper(@PathVariable("productId") Long productId, @RequestBody Map<String,String> map){
        System.out.println(productId);
        System.out.println(map);
        propertiesService.insert(UserService.getCurrentUser(),//this admin want create product
                productId,map);
        return new ResponseEntity<Object>("product properties created", HttpStatus.OK);
    }
}
