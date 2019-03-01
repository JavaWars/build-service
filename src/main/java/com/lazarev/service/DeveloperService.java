package com.lazarev.service;

import com.lazarev.model.Developer;
import com.lazarev.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public List<Developer> allDeveloper(){
        List<Developer> developers=developerRepository.findAll();
        return developers;
    }

    public void delete(long id) {
        developerRepository.deleteById(id);
    }

    public void insert(Developer developer) {
     if (developerRepository.findByPhone(developer.getPhone())==null) {
         developerRepository.save(developer);
     }
    }

    @Transactional
    public void update(long id, Developer developer) {
        Developer d=developerRepository.findById(id).get();
        developer.setId(d.getId());
        insert(developer);
    }

    public Developer developer(long id) {
        return developerRepository.findById(id).get();
    }
}
