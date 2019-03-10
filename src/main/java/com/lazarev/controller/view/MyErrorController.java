package com.lazarev.controller.view;

import com.lazarev.model.File;
import com.lazarev.repository.file.FileInfoDbStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @Autowired
    private FileInfoDbStorage fileInfo;

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(HttpServletRequest httpRequest, Model model){

        File f=fileInfo.getByStorageType("ERR");
        if (f!=null) {
            model.addAttribute("errorLogo", f);
        }

        Object status = httpRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        model.addAttribute("errorMsg", "My default error message");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMsg","404");
            }
            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMsg","500");
            }
        }
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
