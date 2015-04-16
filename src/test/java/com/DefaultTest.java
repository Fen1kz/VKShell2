package com;

import vkshell.main.MainConfig;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class})
@TestPropertySource("classpath:application.test.properties")
@ActiveProfiles("test")
public abstract class DefaultTest {
    protected Logger flogger = LoggerFactory.getLogger("TestLogger");

    @Autowired
    protected ConfigurableApplicationContext ctx;
}
