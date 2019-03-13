package com.lazarev.service;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.model.Product;
import com.lazarev.model.ProductPropertyName;
import com.lazarev.model.ProductPropertyValue;
import com.lazarev.model.User;
import com.lazarev.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PropertiesService {

    @Autowired
    private ProductPropertyNameRepository productPropertyNameRepository;

    @Autowired
    private ProductPropertyValueRepository productPropertyValueRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeveloperRepository developerRepository;

    public void insert(User currentUser, Long productId, Map<String, String> map) {

        if (developerRepository.findByAdminOrOwner(currentUser.getId())!=null) {

            Product product = productRepository.findById(productId).get();
            for (String s : map.keySet()) {//foreach property name

                ProductPropertyName ppn = productPropertyNameRepository.findByName(s);
                if (ppn == null) {
                    ppn = new ProductPropertyName();
                    ppn.setName(s);
                    productPropertyNameRepository.save(ppn);
                }

                String val = map.get(s);
                ProductPropertyValue ppv = productPropertyValueRepository.findByProductValue(val);
                if (ppv == null) {
                    ppv = new ProductPropertyValue();
                    ppv.setProductValue(val);
                    productPropertyValueRepository.save(ppv);
                }

                product.getProductProperties().put(ppn, ppv);

            }
            productRepository.save(product);
        }
        else{
            throw new BuildServiceApplicationException("This user dont have access to developer product");
        }
    }

    public Map<String, String> getPropertiesForProduct(Long productId) {

        Map<String,String> result=new HashMap<>();

        for(Map.Entry<ProductPropertyName,ProductPropertyValue> element: productRepository.getOne(productId).getProductProperties().entrySet()){
            result.put(element.getKey().getName(),element.getValue().getProductValue());
        }
        return result;
    }
}
