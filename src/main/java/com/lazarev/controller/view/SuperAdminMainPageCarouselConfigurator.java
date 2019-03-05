package com.lazarev.controller.view;

import com.lazarev.repository.file.FileInfoDbStorage;
import com.lazarev.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
