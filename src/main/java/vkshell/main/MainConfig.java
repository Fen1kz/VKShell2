package vkshell.main;


import vkshell.app.AppConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("vkshell")
@Import({ AppConfig.class })
//@PropertySource("classpath:application.properties")
@PropertySource("classpath:application.properties")
public class MainConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
