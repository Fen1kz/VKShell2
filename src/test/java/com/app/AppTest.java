package com.app;

import com.DefaultTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Map;

public class AppTest extends DefaultTest {
    @Autowired
    ConfigurableApplicationContext ctx;

    @Autowired
    DefaultListableBeanFactory beanFactory;

    @Autowired
    App app;

    @Test
    @DirtiesContext
    public void clearProperty() {
        Map TestProperties = new HashMap();
        TestProperties.put("App.folder", null);
        ctx.getEnvironment().getPropertySources().addFirst(new MapPropertySource("TestOverride", TestProperties));
        beanFactory.destroySingletons();
        App app = ctx.getBean(App.class);
    }
}
