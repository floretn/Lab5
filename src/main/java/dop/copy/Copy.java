package dop.copy;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class Copy {

    public void copy(String filename1, String filename2) {
        try(BufferedReader br = new BufferedReader(new FileReader(filename1));
            PrintWriter printWriter = new PrintWriter(new FileWriter(filename2)))
        {
            Stream<String> stream = br.lines();
            for (Object s : stream.toArray()){
                printWriter.write( s + "\n");
            }
        }catch (IOException ioe){
            System.err.println("Не найден один из файлов!");
        }
    }

    public void copy2(String filename1, String filename2) {
        PrintWriter printWriter = null;
        BufferedReader br= null;
        try {
            br = new BufferedReader(new FileReader(filename1));
            printWriter = new PrintWriter(new FileWriter(filename2));
            Stream<String> stream = br.lines();
            for (Object s : stream.toArray()){
                printWriter.write( s + "\n");
            }
        }catch (IOException ioe){
            System.err.println("Не найден один из файлов!");
        }finally {

            if (printWriter != null) {
                printWriter.close();
            }

            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Copy cop = new Copy();
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя файла для чтения: ");
        String filenameForRead = sc.next();
        System.out.print("Введите имя файла для записи: ");
        String filenameForWrite = sc.next();
        cop.copy(filenameForRead, filenameForWrite);
    }
}
