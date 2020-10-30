package dop.battle;

import java.io.IOException;

public class MyException extends IOException {
    public MyException() {}

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message) ;
        initCause(cause);
    }
}
