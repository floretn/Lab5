package task10;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task10 {

    public int factorial(int i){
        if (i != 0){
            i = i * factorial(i - 1);
            return i;
        }
        StackTraceElement[] methods = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : methods) {
            System.out.println(ste.toString());
        }
        return 1;
    }

    public static void main(String[] args) {
        System.out.print("Введите число: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        i = (new Task10()).factorial(i);
        System.out.println(i);
        System.out.print("Введите число: ");
        try {
            i = sc.nextInt();
        }catch (InputMismatchException ime){
            ime.printStackTrace(new PrintStream(System.out));
        }
    }
}
