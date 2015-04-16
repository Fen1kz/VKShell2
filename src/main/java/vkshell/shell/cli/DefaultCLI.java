package vkshell.shell.cli;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;

@Component
public class DefaultCLI extends AbstractCLI {
    private BufferedReader console;

    @Override
    public ICLI setIn(InputStream in) {
        super.setIn(in);
        this.console = new BufferedReader(new InputStreamReader(in));
        return this;
    }

    @Override
    public String readLine() throws IOException {
        String inputline = console.readLine();
        return super.processInput(inputline);
    }
}