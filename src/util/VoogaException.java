package util;

import java.io.IOException;

public class VoogaException extends RuntimeException {

	public static final String COMPONENT_TYPE_MISMATCH = "The component %s does not match its type %s";
	public static final String COMPONENT_NOT_EXIST = "Component of type %s does not exist";
	
	private static final long serialVersionUID = 1L;

	public VoogaException(String message) {
		super(message);
	}

	public VoogaException(Throwable cause) {
		super(cause);
	}

	public VoogaException(String message, Object ... values) {
		super(String.format(message, values));
	}

    public static void safeIO(Runnable<IOException> runner) {
        try {
            runner.run();
        } catch (IOException e) {
            throw new VoogaException("IO Error");
        }
    }
    
    public static <T> T safeIO(Supplier<T, IOException> supplier) {
        try {
            return supplier.get();
        } catch (IOException e) {
            throw new VoogaException("IO Error");
        }
    }
	
}
