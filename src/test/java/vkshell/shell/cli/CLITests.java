package vkshell.shell.cli;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import vkshell.DefaultTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CLITests extends DefaultTest {
    StringCommand sc;
    private InputStream SystemIn;
    private PrintStream SystemOut;
    String EOL = System.lineSeparator();


    @Before
    public void before() {
        sc = new StringCommand();
        SystemIn = System.in;
        SystemOut = System.out;
    }

    @After
    public void after(){
        System.setIn(SystemIn);
        System.setOut(SystemOut);
    }

    @Test
    public void ManualCLITEst() throws IOException {
        ICLI cli = ctx.getBean("CLI", ICLI.class);
        while(cli.readLine() != null) {
            System.out.println("+");
        };
    }

    //@Test
    public void DefaultCLITest() throws IOException {
        sc.writeLine("test");
        sc.writeLine("sтест");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        PrintStream p = new PrintStream(bytes);

        ICLI cli = ctx.getBean("CLI", ICLI.class)
                .setEcho(true)
                .setIn(new ByteArrayInputStream(sc.get().getBytes(StandardCharsets.UTF_8)))
//                .setOut(p)
                ;

        cli.readLine();
        cli.readLine();

        Scanner out = new Scanner(bytes.toString());

//        assertEquals("_test_", out.nextLine());
//        assertEquals("_sтест_", out.nextLine());
    }

    private class StringCommand {
        public String str;
        public StringCommand(){
            this.str = "";
        }
        public String get(){
            return str;
        }
        private String writeLine(String command) {
            str += command + "\n";
            return str;
        }
    }
}
