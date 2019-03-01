package com.lazarev.service;

import com.lazarev.exception.NoSuchDeveloper;
import com.lazarev.model.Developer;
import com.lazarev.model.Product;
import com.lazarev.repository.DeveloperRepository;
import com.lazarev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @Autowired
    DeveloperRepository developerRepository;
    public void insert(long developerId, Product product) {
        Developer d=developerRepository.findById(developerId).get();
        if (d==null){
            throw new NoSuchDeveloper("no such developer");
        }
        else{
            d.getProducts().add(product);
            developerRepository.save(d);
        }
    }

    public void delete(long developerId, Product product) {
        Developer d=developerRepository.findById(developerId).get();
        if (d==null){
            throw new NoSuchDeveloper("no such developer");
        }
        else{
            d.getProducts().remove(product);
            developerRepository.save(d);
        }
    }
}
