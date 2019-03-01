package com.lazarev;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import com.lazarev.model.Developer;
import com.lazarev.model.Service;
import com.lazarev.model.User;
import com.lazarev.repository.ServiceRepository;
import com.lazarev.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class StarterTest {

//    private static Logger logger = LogManager.getLogger();
//
//    @Test
//    public void testsLogger() {
//        logger.trace("A TRACE Message");
//        logger.debug("A DEBUG Message");
//        logger.info("An INFO Message");
//        logger.warn("A WARN Message");
//        logger.error("An ERROR Message");
//        assert (true);
//    }

    @Autowired
    private UserRepository userRepository;
    @Test
    public void userTest(){
        userRepository.save(new User());
        userRepository.save(new User());
    }

}