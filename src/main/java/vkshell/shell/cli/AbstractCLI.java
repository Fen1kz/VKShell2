package vkshell.shell.cli;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created on 16.04.2015.
 */
public abstract class AbstractCLI implements ICLI {
    public boolean echo = false;
    protected InputStream in;
    protected PrintStream out;

    public AbstractCLI() {
        this.setIn(System.in);
        this.setOut(System.out);
    }

    @Override
    public ICLI setIn(InputStream in) {
        this.in = in;
        return this;
    }

    @Override
    public ICLI setOut(PrintStream out) {
        this.out = out;
        return this;
    }

    @Override
    public PrintStream out() {
        return out;
    }

    @Override
    public ICLI setEcho(boolean flag) {
        echo = flag;
        return this;
    }

    protected String processInput(String inputline) {
        if (echo) {
            out.println("_" + inputline + "_");
        }
        return (inputline == null) ? null : inputline.trim();
    }
}
