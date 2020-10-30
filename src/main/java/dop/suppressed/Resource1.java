package dop.suppressed;

import dop.battle.MyException;

import java.util.Scanner;

public class Resource1 implements AutoCloseable{

    String msg = "Ресурс один открыт!";

    @Override
    public void close() throws MyException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Исключение - y, иначе что угодно: ");
        String s = sc.next();
        if(s.equals("y")) {
            throw new MyException("Ресурс один дал сбой!");
        }
    }
}
