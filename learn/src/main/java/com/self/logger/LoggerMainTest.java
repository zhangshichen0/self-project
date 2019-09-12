package com.self.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shichen
 * @create 2018/7/26
 * @desc
 */
public class LoggerMainTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerMainTest.class);

    public static void main(String[] args) {
        LOGGER.info("【test logger】 test logger");
    }

}
