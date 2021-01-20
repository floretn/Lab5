package dop.battle;

import java.io.IOException;
import java.util.Scanner;

public class MyException extends IOException {
    public MyException() {}

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message) ;
        initCause(cause);
    }

    public static void main(String[] args) {
        int a = (new Scanner(System.in)).nextInt();
    }
}
