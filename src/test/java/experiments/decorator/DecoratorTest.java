package experiments.decorator;

import org.junit.Test;

/**
 * Created on 16.04.2015.
 */
public class DecoratorTest {
    @Test
    public void TestBase() {
        ICanPrint decorator = null;
        ICanPrintA newA = decorator.addDeco(ICanPrintA.class);
        newA.printA();
        ICanPrintB newB = decorator.addDeco(ICanPrintB.class);
        newB.printB();
        //newB.printA();
    }
}
