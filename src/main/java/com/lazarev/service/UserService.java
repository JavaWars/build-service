package com.lazarev.service;

import com.lazarev.exception.NoSuchUser;
import com.lazarev.exception.UserAlreadyExist;
import com.lazarev.model.security_user.CustomUserDetails;
import com.lazarev.model.Role;
import com.lazarev.model.User;
import com.lazarev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userRepository.findByEmail(username);

        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return optionalUsers
                .map(CustomUserDetails::new).get();

//        User user = userRepository.findByEmail(username);
//        if (userRepository==null) throw new UsernameNotFoundException("Username not found");
//        return new CustomUserDetails(user);
    }

    public void insertNewUser(User u){
        if (userRepository.findByPhone(u.getPhone())==null
                && userRepository.findByEmail(u.getEmail())==null) {
            userRepository.save(u);
        }
        else throw new UserAlreadyExist("user is already in db");
    }

    public User getById(Long id) {
        User u=null;
        if (userRepository.existsById(id)){
            u=userRepository.findById(id).get();
        }
        else
        {
            throw new NoSuchUser("No such user");
        }
        return u;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getByEmail(String email) {
        User u=userRepository.findByEmail(email).get();
        if (u==null){throw new NoSuchUser("no such User");}
        return userRepository.findByEmail(email).get();
    }

    public void delete(Long userId) {

        if (userRepository.existsById(userId)) {
            User u = userRepository.getOne(userId);
            u.setRole(Role.DELETED);
            userRepository.save(u);
        }
        else{
            throw new NoSuchUser("no such user for deliting");
        }
    }

    public void refresh(Long userId) {
        if (userRepository.existsById(userId)) {
            User u = userRepository.getOne(userId);
            u.setRole(Role.USER);
            userRepository.save(u);
        }
        else{
            throw new NoSuchUser("no such user for refreshing");
        }
    }

    public void insertRole(Long userId, Role role) {
        if (userRepository.existsById(userId)) {
            User u = userRepository.getOne(userId);
            u.setRole(role);
            userRepository.save(u);
        }
        else{
            throw new NoSuchUser("no such user for refreshing");
        }
    }
}
