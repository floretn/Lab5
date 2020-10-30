package dop.chain;

import task1_3.FileFormatException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Chain {

    static int counter;

    public void method() throws FileFormatException {
        Scanner sc = new Scanner("chain.txt");
        try {
            int i = sc.nextInt();
        }catch (InputMismatchException ex){
            throw new FileFormatException("Файл содержит некорректные данные: Ожидаются целые числа!", ex);
        }
    }

    public void method2() throws InputMismatchException {
        Scanner sc = new Scanner("chain");
        try {
            int i = sc.nextInt();
        }catch (InputMismatchException ex){
            counter++;
            throw  ex;
        }
    }

    public static void main(String[] args) throws FileFormatException {
        try {
            (new Chain()).method2();
        }catch (InputMismatchException ex){
            System.err.println(ex.getMessage());
        }

        try {
            (new Chain()).method2();
        }catch (InputMismatchException ex){
            System.err.println(ex.getMessage());
        }

        System.out.println(counter);

        (new Chain()).method();
    }
}
