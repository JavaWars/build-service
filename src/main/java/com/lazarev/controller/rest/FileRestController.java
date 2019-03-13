package com.lazarev.controller.rest;

import com.lazarev.model.File;
import com.lazarev.model.Product;
import com.lazarev.repository.file.FileInfoDbStorage;
import com.lazarev.service.ProductImagesService;
import com.lazarev.service.ProductService;
import com.lazarev.service.file.FileService;
import com.lazarev.service.file.FileServiceImpl;
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

    @RequestMapping(value = "/memory_img/err",method = RequestMethod.GET)
    public ResponseEntity<Object> getErrImgFile(){
        return new ResponseEntity<Object>(memFileService.getById(fileInfoDbStorage.findByStorageType("ERR").getId()).getValue(), HttpStatus.OK);
    }

    @RequestMapping(value = "/memory_img/err_access",method = RequestMethod.GET)
    public ResponseEntity<Object> getErrorAccessImgFile(){
        return new ResponseEntity<Object>(memFileService.getById(fileInfoDbStorage.findByStorageType("ERR_ACCESS").getId()).getValue(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @RequestMapping(value = "/memory_img/{fileId}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteCarouselImgFile(@PathVariable("fileId")Long id){
        memFileService.delete(id);
        return new ResponseEntity<Object>("deleted", HttpStatus.OK);
    }

    @Autowired
    private ProductImagesService productImagesService;

    @PreAuthorize("hasAnyRole('SUPERADMIN','ADMIN')")
    @RequestMapping(value = "/product_img/{productId}",method = RequestMethod.POST)
    public ResponseEntity<Object> saveFileDeveloperProductImage(@PathVariable("productId") Long productId,@RequestParam("files") MultipartFile[] files){

        productImagesService.saveAllImagesForProduct(files, productId);
        return new ResponseEntity<>("files was saved to disk", HttpStatus.OK);
    }

    @RequestMapping(value = "/product_img/{imageId}",method = RequestMethod.GET)
    public ResponseEntity<Object> getProductImage(@PathVariable("imageId") Long imageId){

        return new ResponseEntity<>(diskFileService.getById(imageId).getValue(), HttpStatus.OK);
    }

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product_root_img/{productId}",method = RequestMethod.GET)
    public ResponseEntity<Object> getRootProductImage(@PathVariable("productId") Long productId){
        Product p=productService.findProductById(productId);
        if (p.getImages().size()>0) {
            return new ResponseEntity<>(diskFileService.getById(
                    p.getImages().get(0).getId()//get root images id
            ).getValue(), HttpStatus.OK);
        }
        return new ResponseEntity<Object>("no root product image", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @RequestMapping(value = "/memory_img",method = RequestMethod.POST)
    public String saveFile(@RequestParam("file") MultipartFile file,String logoType){
        ((FileServiceImpl)memFileService).save(file,logoType);
        return "image saved in memory";
    }
}
