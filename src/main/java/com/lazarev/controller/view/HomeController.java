package com.lazarev.controller.view;

import com.lazarev.model.File;
import com.lazarev.repository.file.FileInfoDbStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private FileInfoDbStorage fileInfo;

    @RequestMapping(value = {"/", "/home", "index"},method = RequestMethod.GET)
    public String home(Model model) {
        //// TODO: 05.03.2019 LOAD IMAGES FROM MEMORY
        model.addAttribute("carousel_list",fileInfo.getAllByStorageType("MEMORY"));
        return "index";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String getRegistrationPage(Model model){
        //// TODO: 06.03.2019 if user logined redirect
        File fInfo=fileInfo.getRegistrationLogo();
        if (fInfo!=null) {
            model.addAttribute("logo", fInfo);
        }
        return "common_pages/user_registration";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String getLoginPage(Model model){
        //// TODO: 06.03.2019 if user logined redirect
        File fInfo=fileInfo.getRegistrationLogo();
        if (fInfo!=null) {
            model.addAttribute("logo", fInfo);
        }
        return "common_pages/user_login";
    }
}
