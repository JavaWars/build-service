package com.lazarev.service;

import com.lazarev.exception.NoSuchCategory;
import com.lazarev.model.Category;
import com.lazarev.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(String name) {
        Category c=null;
        c=categoryRepository.findByCategoryName(name);
        if (c==null){
            throw new NoSuchCategory("No such category");
        }
        return c;
    }

    public Category getCategory(Long id) {
        Category c=null;
        if (categoryRepository.existsById(id)){
            c=categoryRepository.findById(id).get();
        }
        else
        {
            throw new NoSuchCategory("No such category");
        }
        return c;
    }
}
