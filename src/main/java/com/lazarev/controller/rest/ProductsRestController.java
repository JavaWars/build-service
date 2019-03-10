package com.lazarev.controller.rest;

import com.lazarev.model.Product;
import com.lazarev.service.ProductService;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/api")
@RestController
public class ProductsRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public ResponseEntity<Object> products(){
        return new ResponseEntity<Object>(productService.allProduct(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public ResponseEntity<Object> insertProductForDeveloper(@RequestBody Product product){

        Long insertedId=productService.insert(UserService.getCurrentUser(),//this admin want create product
                product);
        return new ResponseEntity<Object>(insertedId,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/products",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProductForDeveloper(@RequestBody Product product){
        productService.delete(1l,product);
        return new ResponseEntity<Object>("product created",HttpStatus.OK);
    }
}
