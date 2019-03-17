package com.lazarev.controller.view;

import com.lazarev.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CartController {

    @Autowired
    private ShoppingCartService cartService;

    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    public String getCartProducts(Model model){

        model.addAttribute("cart_elements",cartService.getAllOrders());
        model.addAttribute("tatal_price",cartService.getTotalPrice());
        return "common_pages/cart";
    }


    //
    @RequestMapping(value = "/current_cart_total_price",method = RequestMethod.GET)
    public ResponseEntity<Object> getCartProducts(){
        return new ResponseEntity<>(cartService.getTotalPrice(),HttpStatus.OK);
    }

    @RequestMapping(value = "/cart",method = RequestMethod.POST)
    public ResponseEntity<Object> addProductToCart(Long productId, Integer count){

        cartService.addToCart(productId,count);
        return new ResponseEntity<>("product added to shopping cart", HttpStatus.OK);
    }

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','SUPERADMIN')")
    @RequestMapping(value = "/order_and_get_check",method = RequestMethod.GET)
    public ResponseEntity<Object> orderCurrentCart(){
        //todo return pdf check
        cartService.makeOrderNow();
        return new ResponseEntity<>("Order created", HttpStatus.OK);
    }

    @RequestMapping(value = "/cart",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProductFromCart(Long productId, HttpServletResponse response){

        System.out.println("DELETE /cart"+productId);

        cartService.deleteFromCart(productId);
        return new ResponseEntity<>("product deleted from shopping cart", HttpStatus.OK);
    }

}
