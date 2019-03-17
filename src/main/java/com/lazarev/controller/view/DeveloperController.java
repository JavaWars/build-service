package com.lazarev.controller.view;

import com.lazarev.model.Role;
import com.lazarev.model.User;
import com.lazarev.service.DeveloperService;
import com.lazarev.service.OrderService;
import com.lazarev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @RequestMapping(value = "/become_developer",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String becomeDeveloper(Model model) {

        //rewrite this
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = authentication == null ? null : (User) authentication.getPrincipal();

        model.addAttribute("developerInfo",developerService.getByUserId(u.getId()));

        if (u.getRole()== Role.ADMIN){return "redirect:/developer_management";}

        return "common_pages/become_developer";
    }


    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/developer_management",method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String getManagemantPage(Model model) {

        model.addAttribute("developerInfo",developerService.getByUserId(UserService.getCurrentUser().getId()));
        model.addAttribute("dev_order_list",orderService.getOrdersByCurrentDeveloper(developerService.getDeveloperByCurrentUserId(UserService.getCurrentUser().getId()).getId()));
        model.addAttribute("isOwner",developerService.isCurrentUserOwnerOfCompany());
        return "admin_pages/developer_management";
    }

}
