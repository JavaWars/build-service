package com.lazarev.controller.rest;

import com.lazarev.repository.file.FileInfoDbStorage;
import com.lazarev.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class FileRestController {

    @Autowired
    @Qualifier("diskFileService")
    FileService diskFileService;

    @Autowired
    @Qualifier("memoryFileService")
    FileService memFileService;

    @Autowired
    private FileInfoDbStorage fileInfoDbStorage;

    @RequestMapping(value = "/memory_img/{fileId}",method = RequestMethod.GET)
    public ResponseEntity<Object> getCarouselImgFile(@PathVariable("fileId")Long id){
        return new ResponseEntity<Object>(memFileService.getById(id).getValue(), HttpStatus.OK);
    }

    @RequestMapping(value = "/memory_img/logo",method = RequestMethod.GET)
    public ResponseEntity<Object> getLogoImgFile(){
        return new ResponseEntity<Object>(memFileService.getById(fileInfoDbStorage.getRegistrationLogo().getId()).getValue(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @RequestMapping(value = "/memory_img/{fileId}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCarouselImgFile(@PathVariable("fileId")Long id){
        memFileService.delete(id);
        return new ResponseEntity<Object>("deleted", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    @RequestMapping(value = "/product_img",method = RequestMethod.POST)
    public ResponseEntity<Object> saveFileDeveloperProductImage(@RequestParam("file") MultipartFile file){
        diskFileService.save(file);
        return new ResponseEntity<>("file was saved to disk", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @RequestMapping(value = "/memory_img",method = RequestMethod.POST)
    public String saveFile(@RequestParam("file") MultipartFile file,String siteImgType){
        //// TODO: 05.03.2019  different siteImgType
        memFileService.save(file);
        return "image saved in memory";
    }
}
