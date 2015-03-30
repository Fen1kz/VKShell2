package com.app;//package com.app;

import com.main.MainConfig;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MainConfig.class})
@TestPropertySource("classpath:application.test.properties")
@ActiveProfiles("test")
public class AppTestProperty {
    @Autowired
    ConfigurableApplicationContext ctx;


    @Test
    public void normal() {
        System.out.println("normal");

        App app = ctx.getBean(App.class);
        System.out.println(app);

//        ctx.get
        //ctx.getEnvironment().getPropertySources().remove("TestOverride");
    }
}
