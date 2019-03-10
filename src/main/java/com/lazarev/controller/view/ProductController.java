package com.lazarev.controller.view;

import com.lazarev.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    @RequestMapping(value = {"/product_config"},method = RequestMethod.GET)
    public String get_ProductConfig(){
        return "fragments/developer_for_admins/new_product";
    }

}
