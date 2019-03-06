package com.lazarev.controller.rest;

import com.lazarev.service.OrderService;
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
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public ResponseEntity<Object> getUserOrders(@RequestBody Long userId){
        return new ResponseEntity<Object>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
