package com.lazarev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @RequestMapping(value = {"/products"},method = RequestMethod.GET)
    public String products(){
        return "common_pages/products";
    }

}
