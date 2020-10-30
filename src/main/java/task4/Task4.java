package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1.       Как правильно обрабатывать исключения в finally.
 *
 * 2.       Chained – когда? Слои – ОК. + можно привязывать проверяемое исключение в методе, в котором нельзя генерить искл-я
 *
 * 3.       Suppressed – пример + ?
 *
 * 4.       Try-with-res -> try finally ?
 *
 * 5.       Suppressed vs chained
 *
 * 6.       Exceptions vs error codes +
 */

public class Task4 {

    private double sum;

    public int readValues(String filename, ArrayList<Double> arrayList) {
        File file = new File(filename);
        Scanner sc;
        try {
            sc = new Scanner(file);
        }catch (FileNotFoundException ex){
            return -2;
        }
        while (sc.hasNext()){
            try {
                arrayList.add(sc.nextDouble());
            }catch (java.util.InputMismatchException ex){
                return -1;
            }
        }
        return 0;
    }

    public int sumOfValues(String filename){
        ArrayList<Double> arrayList = new ArrayList<Double>();
        int rez = (new Task4()).readValues(filename, arrayList);
        if (rez == 0) {
            for (double s : arrayList) {
                sum += s;
            }
        }
        return rez;
    }

    public double getSum() {
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename;
        int rez;
        ArrayList<Double> arrayList = new ArrayList<Double>();
        Task4 task4 = new Task4();
        while(true){
            System.out.print("Введите имя файла: ");
            filename = sc.next();
            if (filename.equals("exit")){
                break;
            }
            System.out.print("1 или 2?: ");
            rez = sc.nextInt();

            if (rez != 1 && rez != 2){
                System.out.println("Нет такого варианта...");
                continue;
            }

            if (rez == 1) {
                rez = task4.readValues(filename, arrayList);
                if (rez == 0) {
                    System.out.println(arrayList);
                }
                if (rez == -2) {
                    System.out.println("Файл не был найден!");
                }
                if(rez == -1) {
                    System.out.println("Файл содержит не только числа с плавающей запятой!");
                }
            }

            if (rez == 2){
                rez = task4.sumOfValues(filename);
                if (rez == 0) {
                    System.out.println(task4.sum);
                }
                if(rez == -2) {
                    System.out.println("Файл не был найден!");
                }
                if(rez == -1) {
                    System.out.println("Файл содержит не только числа с плавающей запятой!");
                }
            }
        }
    }
}