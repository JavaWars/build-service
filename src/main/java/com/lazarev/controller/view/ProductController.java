package com.lazarev.controller.view;

import com.lazarev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product/{productId}",method = RequestMethod.GET)
    public String product(@PathVariable("productId")Long productId, Model model){

        model.addAttribute("product",productService.findProductById(productId));

        return "common_pages/product";
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String products(){
        return "common_pages/search_product";
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    @RequestMapping(value = "/product_config",method = RequestMethod.GET)
    public String get_ProductConfig(Long productIdForConfiguration,Model model){

        if (productIdForConfiguration!=null && productIdForConfiguration>0){
            System.out.println("product configuration"+productIdForConfiguration);
            model.addAttribute("prodId",productIdForConfiguration);
        }

        return "fragments/developer_for_admins/new_product";
    }

}
