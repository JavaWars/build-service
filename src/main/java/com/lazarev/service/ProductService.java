package com.lazarev.service;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.exception.NoSuchDeveloper;
import com.lazarev.model.Developer;
import com.lazarev.model.Product;
import com.lazarev.model.User;
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

    public Long insert(User whoWantInsert, Product product) {

        Developer d=developerRepository.findByAdminOrOwner(whoWantInsert.getId());
        if (d!=null){
            System.out.println("we will insert into"+d);
            System.out.println("this product"+product);
            //properties or values duplication

            productRepository.save(product);//andFlush
            System.out.println("1saved "+product);
            product.setDeveloper(d);

            d.getProducts().add(product);
            developerRepository.save(d);

            return product.getId();
        }
        else{
            throw new BuildServiceApplicationException("this user dont have developer");
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
