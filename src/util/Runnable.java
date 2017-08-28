package util;

@FunctionalInterface
public interface Runnable<T extends Exception> {

    void run() throws T;
    
}
