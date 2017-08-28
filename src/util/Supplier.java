package util;

@FunctionalInterface
public interface Supplier<T, E extends Exception> {

    T get() throws E;
    
}
