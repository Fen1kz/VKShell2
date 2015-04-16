package vkshell.main;


import vkshell.app.AppConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import vkshell.shell.cli.DefaultCLI;
import vkshell.shell.cli.ICLI;
import vkshell.shell.cli.JLine2CLI;

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

    @Bean(name = "CLI")
    public ICLI getCli() {
        return new DefaultCLI();
//        return new JLine2CLI();
    }
}
