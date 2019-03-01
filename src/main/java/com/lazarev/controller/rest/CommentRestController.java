package com.lazarev.controller.rest;

import com.lazarev.model.Comment;
import com.lazarev.repository.CommentRepository;
import com.lazarev.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN','USER')")
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public ResponseEntity<Object> insertComment(@RequestBody Comment comment){
        //// TODO: 01.03.2019  who send comment and for product id?
        commentService.inset(comment);
        return new ResponseEntity<>("comment posted", HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{productId}",method = RequestMethod.GET)
    public ResponseEntity<Object> getComments(@PathVariable("productId")Long productId){
        System.out.println(productId);
        return new ResponseEntity<Object>(commentService.getCommentByProductId(productId), HttpStatus.OK);
    }
}
