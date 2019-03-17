package com.lazarev.controller.rest;

import com.lazarev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public ResponseEntity<Object> getUserOrders(){
        return new ResponseEntity<Object>(orderService.getOrdersByCurrentUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/cancel_order/{orderId}",method = RequestMethod.PUT)
    public ResponseEntity<Object> cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return new ResponseEntity<Object>("order_canceled", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/dev_sent_order/{orderId}",method = RequestMethod.PUT)
    public ResponseEntity<Object> developerSentOrder(@PathVariable("orderId") Long orderId){
        orderService.sentOrder(orderId);
        return new ResponseEntity<Object>("order send", HttpStatus.OK);
    }
}
