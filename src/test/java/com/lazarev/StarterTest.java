package com.lazarev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class StarterTest {

    private static Logger logger = LogManager.getLogger();

    @Test
    public void testsLogger() {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
        assert (true);
    }
}