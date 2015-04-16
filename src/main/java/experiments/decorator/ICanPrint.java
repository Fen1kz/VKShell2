package experiments.decorator;

/**
 * Created on 16.04.2015.
 */
public interface ICanPrint {
    <T extends ICanPrint> T addDeco(Class<T> iface);
}
