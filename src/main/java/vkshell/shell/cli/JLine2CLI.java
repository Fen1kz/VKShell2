package vkshell.shell.cli;

import jline.Terminal;
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

@Component
public class JLine2CLI extends AbstractCLI {
    private ConsoleReader console;

    @Override
    public ICLI setIn(InputStream in) {
        super.setIn(in);
        setup();
        return this;
    }

    @Override
    public ICLI setOut(PrintStream out) {
        super.setOut(out);
        setup();
        return this;
    }

    private void setup() {
//        TerminalFactory.configure(TerminalFactory.WIN); // for IDEA console o_O
        Terminal terminal = TerminalFactory.get();
        try {
            terminal.init();
            //terminal.setEchoEnabled(false);
            console = new ConsoleReader(this.in, this.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readLine() throws IOException {
        String inputline = console.readLine();
        return super.processInput(inputline);
    }
}