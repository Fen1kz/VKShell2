package com.app;//package com.app;

import vkshell.app.App;
import vkshell.main.MainConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
