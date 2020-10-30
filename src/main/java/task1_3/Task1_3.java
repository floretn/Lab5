package task1_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1_3 {

    public static ArrayList<Double> readValues(String filename) throws FileNotFoundException, FileFormatException {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            try {
                arrayList.add(sc.nextDouble());
            }catch (java.util.InputMismatchException ex){
                throw  new FileFormatException("File must contain double numbers");
            }
        }
        return arrayList;
    }

    public static double sumOfValues(String filename) throws FileNotFoundException, FileFormatException {
        double sum = 0;
        ArrayList<Double> arrayList = readValues(filename);
        for (double s : arrayList){
            sum += s;
        }
        return sum;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String filename;
        double rez;
        ArrayList<Double> arrayList;
        while(true){
            System.out.print("Введите имя файла: ");
            filename = sc.next();
            if (filename.equals("exit")){
                break;
            }
            System.out.print("1 или 2?: ");
            rez = sc.nextDouble();

            if (rez != 1.0 && rez != 2.0){
                System.out.println("Нет такого варианта...");
            }

            if (rez == 1.0) {
                try {
                    arrayList = readValues(filename);
                    System.out.println(arrayList);
                }catch (FileNotFoundException ex){
                    System.out.println("Файл не был найден!");
                }catch (FileFormatException ex){
                    System.out.println("Файл содержит не только числа с плавающей запятой!");
                }
            }

            if (rez == 2.0){
                try {
                    rez = sumOfValues(filename);
                    System.out.println(rez);
                }catch (FileNotFoundException ex){
                    System.out.println("Файл не был найден!");
                }catch (FileFormatException ex){
                    System.out.println("Файл содержит не только числа с плавающей запятой!");
                }
            }
        }
    }
}