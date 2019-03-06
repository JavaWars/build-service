package com.lazarev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @RequestMapping(value = {"/product/{productId}"},method = RequestMethod.GET)
    public String product(@PathVariable("productId")Long productId){
        return "common_pages/product";
    }

    @RequestMapping(value = {"/products"},method = RequestMethod.GET)
    public String products(){
        return "common_pages/search_product";
    }

    //    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    @RequestMapping(value = {"/product_config"},method = RequestMethod.GET)
    public String get_ProductConfig(){
        return "admin_pages/new_product";
    }


}
