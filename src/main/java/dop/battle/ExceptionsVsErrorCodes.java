package dop.battle;

import java.util.InputMismatchException;
import java.util.Scanner;

// 1) Как возвращать ошибку и данные одновременно
// 2) Сделать вариант Copy с двумя файлами. Транзакционное перемещение файла.
// Если получилось, то ок, старый файл удаляется, новый содержит старый. Если не получилось, то старый остаётся, новый удаляется.

public class ExceptionsVsErrorCodes {

    static Scanner sc = new Scanner(System.in);

    public static int inputYearEx() throws MyException {
        int year = sc.nextInt();
        if (year < 2000 || year > 2020){
            throw new MyException("Год не соответствует допустимому...");
        }
        return year;
    }

    public  static int inputYearErr(){
        int year;
        try{
            year = sc.nextInt();
        }catch (InputMismatchException ex){
            return -1;
        }
        if (year < 2000 || year > 2020){
            return -2;
        }
        return year;
    }

    public static double inputIncomeEx() throws MyException {
        double income = sc.nextDouble();
        if (income < 100.01 || income > 10000000.02){
            throw new MyException("Такого дохода быть не может!!!");
        }
        return income;
    }

    public static double inputIncomeErr(){
        double income;
        try{
            income = sc.nextDouble();
        }catch (InputMismatchException ex){
            return -1;
        }
        if (income < 100.01 || income > 10000000.02){
            return -2;
        }
        return income;
    }

    public static void methodEx() throws MyException {
        int year;
        double income;
        year = inputYearEx();
        income = inputIncomeEx();
        System.out.println("Year = " + year + "; income = " + income);
    }

    public static int methodErr(int[] mass){
        int year;
        double income;
        year = inputYearErr();
        income = inputIncomeErr();
        if (year == -1 || income == -1){
            System.err.println("Ошибка ввода!");
            return -1;
        }
        if (year == -2 || income == -2){
            System.err.println("Некорректные данные!");
            return -2;
        }
        System.out.println("Year = " + year + "; income = " + income);
        mass[0] = year;
        return 0;
    }

    public static void main(String[] args) {



        int[] year = new int[1];
        int check = methodErr(year);
        System.out.println("Работает! " + year[0]);
        if (check == 1){
            System.out.println("Готово!");
        }else{
            System.err.println("Плохой результат!");
        }
    }
}
