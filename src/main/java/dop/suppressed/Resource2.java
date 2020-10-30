package dop.suppressed;

import dop.battle.MyException;

import java.util.Scanner;

public class Resource2 implements AutoCloseable{

    String msg = "Ресурс два открыт!";

    @Override
    public void close() throws MyException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Исключение - y, иначе что угодно: ");
        String s = sc.next();
        if(s.equals("y")) {
            throw new MyException("Ресурс два дал сбой!");
        }
    }
}
