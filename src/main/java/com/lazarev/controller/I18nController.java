package com.lazarev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class I18nController {
    @RequestMapping({"/language","/I18n"})
    public String getInternationalPage() {
        return "language";
    }
}
