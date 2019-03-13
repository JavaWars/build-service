package com.lazarev.service;

import com.lazarev.model.File;
import com.lazarev.model.Product;
import com.lazarev.model.User;
import com.lazarev.repository.DeveloperRepository;
import com.lazarev.repository.ProductRepository;
import com.lazarev.repository.UserRepository;
import com.lazarev.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImagesService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("diskFileService")
    private FileService diskFileService;

    public void saveAllImagesForProduct(MultipartFile[] files, Long productId) {

        Product p = productRepository.findByProductIdAndCurrentUser(productId,UserService.getCurrentUser().getId());
        if (p != null) {
            p.getImages().clear();
            for (MultipartFile mf : files) {
                File newFile = new File();
                diskFileService.save(mf, newFile);//physical saving and db file main info
                p.addNewImage(newFile);
            }
        productRepository.save(p);
        }
    }
}
