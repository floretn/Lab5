package task5;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task5 {

    public String myMethod2(String filename) throws Exception {
        String fromFile = null;
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        Scanner in = null;
        PrintWriter out = null;
        Exception exception = null;
        try
        {
            in = new Scanner(filename);
            out = new PrintWriter(filename);
            if (in.hasNext()) {
                sb.append(in.next()).append(" ");
            }
            System.out.print("Введите строку для записи в файл: ");
            fromFile = sc.next();
            out.print(fromFile);
            fromFile = sb.toString();
        }catch (FileNotFoundException | IllegalStateException ex){
            exception = ex;
            throw exception;
        } finally{
            try{
                if (in != null) {
                    in.close();
                }
            }catch (IllegalStateException ex){
                if (exception != null) {
                    exception.addSuppressed(ex);
                }else{
                    exception = ex;
                    throw exception;
                }
            } finally{
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IllegalStateException ex) {
                    if (exception != null) {
                        exception.addSuppressed(ex);
                    } else {
                        exception = ex;
                        throw exception;
                    }
                }
            }
        }
        return fromFile;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String filename = sc.next();
        filename = (new Task5()).myMethod2(filename);
        System.out.println("Считано из файла: ");
        System.out.println(filename);
    }
}