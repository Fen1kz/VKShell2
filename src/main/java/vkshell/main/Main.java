package vkshell.main;

import vkshell.app.App;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static final String DIRECTORY_PATH = "D:\\test\\cli-nio";
    public static final Path dir = Paths.get(DIRECTORY_PATH);

    public static void main(String[] args) {
//        App app = new App();
//        app.clean();
//        app.createFiles();
//        app.merge("result");

//        HashMap m = new HashMap();
//        System.out.println(m.put("1", "hey"));
//            System.out.println(m.put("1", "howdy"));
//            System.out.println(m.put("1", "huh?"));
//            System.out.println(m.put("2", "ok"));
//            System.out.println(m.put("3", "good"));
//            System.out.println(m.put("3", "good"));
//            System.out.println(m.put("3", "good"));
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                MainConfig.class);

        App app = ctx.getBean(App.class);
    }
}
