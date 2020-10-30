package task1_3;

import java.io.IOException;

public class FileFormatException extends IOException {

    public FileFormatException() {}

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, Throwable cause) {
        super(message) ;
        initCause(cause);
    }
}
