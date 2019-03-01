package com.lazarev.controller.rest;

import com.lazarev.model.Product;
import com.lazarev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ProductsRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public ResponseEntity<Object> products(){
        return new ResponseEntity<Object>(productService.allProduct(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products",method = RequestMethod.POST)
    public ResponseEntity<Object> insertProductForDeveloper(@RequestBody Product product, @RequestParam("developer_id")String id){
        productService.insert(Long.parseLong(id),product);
        return new ResponseEntity<Object>("product created",HttpStatus.OK);
    }

    @RequestMapping(value = "/products",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProductForDeveloper(@RequestBody Product product, @RequestParam("developer_id")String id){
        productService.delete(Long.parseLong(id),product);
        return new ResponseEntity<Object>("product created",HttpStatus.OK);
    }
}
