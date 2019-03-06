package com.lazarev.service;

import com.lazarev.model.Developer;
import com.lazarev.model.User;
import com.lazarev.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
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

    public void insert(Developer developer, User founder) {
        Calendar cal = Calendar.getInstance();
        developer.setFoundation(cal.getTime());

        developer.setFounder(founder);
        //// TODO: 06.03.2019  set founder role admin
        if (developerRepository.findByPhone(developer.getPhone())==null) {
            developerRepository.save(developer);
//            developer.setFounder(founder);
//            developerRepository.save(developer);
        }
    }

    @Transactional
    public void update(long id, Developer developer) {
        Developer d=developerRepository.findById(id).get();
        developer.setId(d.getId());
        //not change the foundуr
        insert(developer,d.getFounder());
    }

    public Developer developer(long id) {
        return developerRepository.findById(id).get();
    }
}
