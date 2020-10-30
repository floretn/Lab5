package task6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Task6 {

    public String method1(String path) {
        BufferedReader in = null;
        String rez = null;
        try {
            in = Files.newBufferedReader(Paths.get(path));
            rez = in.readLine();
        } catch (IOException ex) {
            System.err.println("Caught IOException: " + ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close(); // ВНИМАНИЕ: может быть сгенерировано исключение!
                } catch (IOException e) {
                    System.err.println("Caught IOException: " + e.getMessage());
                }
            }
        }
        return rez;
    }

    public String method2(String path) throws IOException {
        BufferedReader in = null;
        String rez = null;
        try {
            in = Files.newBufferedReader(Paths.get(path));
            rez = in.readLine();
            try {
                in.close(); // ВНИМАНИЕ: может быть сгенерировано исключение!
            }finally {
                System.out.println("Ну закончил");
            }
        } catch (IOException ex) {
            System.err.println("Caught IOException: " + ex.getMessage());
            try {
                in.close(); // ВНИМАНИЕ: может быть сгенерировано исключение!
            }finally {
                System.out.println("Ну закончил");
            }
        }
        return rez;
    }

    public String method3(String path) throws IOException {
        String rez = null;
        try (BufferedReader in = Files.newBufferedReader(Paths.get(path))) {
            rez = in.readLine();
        } catch (IOException ex) {
            System.err.println("Caught IOException: " + ex.getMessage());
        }
        return rez;
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Введите директорию: "); // D:/NIR/Laboratornaya5/task6.txt
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Task6 task6 = new Task6();
        String rez = task6.method1(s);
        System.out.println(rez);
    }
}
