package com.lazarev.controller.view;

import com.lazarev.repository.file.FileInfoDbStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuperAdminMainPageCarouselConfigurator {

    @Autowired
    private FileInfoDbStorage fileInfoDbStorage;

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @RequestMapping(value = "/carousel",method = RequestMethod.GET)
    public String getAllElementsInCarousel(Model model){
        model.addAttribute("carousel_elements",fileInfoDbStorage.findAllInMemory());
        return "admin_pages/carousel_config";
    }

}
