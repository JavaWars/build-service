package com.lazarev.service;

import com.lazarev.exception.BuildServiceApplicationException;
import com.lazarev.model.Developer;
import com.lazarev.model.Role;
import com.lazarev.model.User;
import com.lazarev.repository.DeveloperRepository;
import com.lazarev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    public List<Developer> allDeveloper(){
        List<Developer> developers=developerRepository.findAll();
        return developers;
    }

    public void deleteAdmin(long adminId, long userIdWhoWandToDelete) {

        if (adminId != userIdWhoWandToDelete) {
            Developer d = developerRepository.findByUserId(userIdWhoWandToDelete);//by owner id
            d.removeAdmin(userRepository.getOne(adminId));
            System.out.println(d.getDeveloperAdminsSquade());
            developerRepository.save(d);
            developerRepository.flush();

            //back user status from admin to user
            User freeDobby = userRepository.getOne(adminId);
            freeDobby.setRole(Role.USER);
            freeDobby.setD(null);
            userRepository.save(freeDobby);
        }
        else{
            throw  new BuildServiceApplicationException("ya cant delete yourself from you company");
        }
    }

    @Autowired
    UserRepository userRepository;

    public void insert(Developer developer, Long founderId) {
        System.out.println("inserting developer"+developer);

        Developer dbDeveloperInfo=developerRepository.findByUserId(founderId);

        if (dbDeveloperInfo==null){
            Calendar cal = Calendar.getInstance();
            developer.setFoundation(cal.getTime());

            //update user
            User founder=userRepository.getOne(founderId);
            founder.setRole(Role.ADMIN);
            founder.setD(developer);
            developer.setFounder(founder);
            developerRepository.save(developer);
            userRepository.save(founder);
        }
        else{
            developer.setId(dbDeveloperInfo.getId());
            developer.setFounder(dbDeveloperInfo.getFounder());
            developerRepository.save(developer);
        }
    }

    public Developer developer(long id) {
        return developerRepository.findById(id).get();
    }

    public Developer getById(Long id){
        return developerRepository.findByUserId(id);//userRepository.getById(id);
    }

    public void setNewAdminForDeveloper(Long newAdminId, User currentUser) {
        User newAdmin=userRepository.getOne(newAdminId);
        if (newAdmin.getRole()!=Role.USER){
            throw new BuildServiceApplicationException("Incorrect user role for setting like admin (user already admin or doues not exist)");
        }
        else{
            newAdmin.setRole(Role.ADMIN);
            userRepository.save(newAdmin);
            Developer d=developerRepository.findByUserId(currentUser.getId());
            d.addAdmin(newAdmin);
            newAdmin.setD(d);
            developerRepository.save(d);
        }
    }

    public Set<User> getAdminsForDeveloper(long developerOwnerId) {
        return developerRepository.findByUserId(developerOwnerId).getDeveloperAdminsSquade();
    }

    public Developer getDeveloperByCurrentUserId(long id) {
        return developerRepository.findByAdminOrOwner(id);
    }
}
