package com.lazarev.controller.view;

import com.lazarev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/user_cabinet",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER','ADMIN','SUPERADMIN')")
    public String usersCabinetInfo(Model model) {

        model.addAttribute("user_orders",orderService.getOrdersByCurrentUser());
        return "user_pages/user_cabinet";
    }
}
