package dop.chain;

import dop.battle.MyException;

import java.util.Scanner;

public class Chain2 {

    public int inputYear() throws MyException {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        if (year < 2000 || year > 2020){
            throw new MyException("Год не соответствует допустимому...");
        }
        return year;
    }
}
