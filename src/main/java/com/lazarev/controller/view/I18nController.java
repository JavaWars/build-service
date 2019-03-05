package com.lazarev.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class I18nController {
    @RequestMapping(value = {"/language", "/I18n"},method = RequestMethod.GET)
    public String getInternationalPage() {
        return "common_pages/language";
    }
}
