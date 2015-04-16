package vkshell.shell.cli;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

@Lazy
@Component
public interface ICLI {
    ICLI setIn(InputStream in);

    ICLI setOut(PrintStream out);

    ICLI setEcho(boolean flag);

    String readLine() throws IOException;

    PrintStream out();
}
